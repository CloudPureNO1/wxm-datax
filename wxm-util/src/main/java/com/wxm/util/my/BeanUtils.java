package com.wxm.util.my;


import com.wxm.base.enums.UtilEnum;
import com.wxm.base.exception.UtilException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * <p>BeanUtils 性能</p>
 * <p>
 * 速度，毫秒
 * 100次    1000次	  1万次   亿次
 * Cglib BeanCopier	     0	      0	       3	  15
 * getAndSet		     0	      0	       0	  7
 * apache PropertyUtils	 85	      106	   216    386233
 * apache BeanUtils      11	      40	   179    668751
 * spring BeanUtils	     80	      63	   86     33286
 *
 * </p>
 * <p>
 * 1. 手动get&set实现：
 * 简单，粗暴，高性能
 * <p>
 * 2. 通过Cglib的BeanCopier实现（使用动态代理，效率高）：
 * 3. 通过Apache的BeanUtils实现（反射机制）：
 * 4. 通过Apache的PropertyUtils实现（反射机制）：
 * 5. 通过Spring的BeanUtils实现（反射机制）：
 * </p>
 * <p>
 * 综上，性能测试结果：getAndSet > Cglib BeanCopier > apache BeanUtils  >  spring BeanUtils > apache PropertyUtils
 * bean copy场景较少或者对性能要求较高的部分避免使用任何bean copy框架
 * 如果要使用bean copy框架，优先使用cglib。同时需要注意：cglib使用BeanCopier.create()也是非常耗时，避免多次调用，尽可能做成全局初始化一次
 * </p>
 * <p>
 * {@link BeanCopier} copy 的对象的setter 方法必返回值必须是void ，否则不能拷贝值
 *
 * @author 王森明
 * @Accessors(chain = true)  类同于给setter 方法返回了当前对象，不能拷贝值
 * </p>
 * @date 2022/6/8 10:29
 * @since 1.0.0
 */
@Slf4j
public class BeanUtils {
    /**
     * 饿汉式单例模式
     */
/*    private static BeanUtils instance = new BeanUtils();
    private BeanUtils(){}
    public static BeanUtils getInstance() {
        return instance;
    }*/

    /**
     * 双检锁/双重校验锁（DCL，即 double-checked locking） 单例模式
     */
    private volatile static BeanUtils instance;

    private BeanUtils() {
    }

    public static BeanUtils getInstance() {
        if (instance == null) {
            synchronized (BeanUtils.class) {
                if (instance == null) {
                    instance = new BeanUtils();
                }
            }
        }
        return instance;
    }

    //
    /**
     * byte boolean char short int flout long  double
     * <p>
     * 默认转String  (byte,boolean 和其包装类型除外)
     * <p>
     * Date , {@link java.math.BigDecimal}  ，List ,Map 等除外
     */
    private static final Class[] TO_STRING_CLASS_TYPES = {Boolean.class,Character.class, Short.class, Integer.class, Long.class, Float.class, Double.class};

    /**
     * 分段的数组+链表实现
     * ConcurrentHashMap允许多个修改操作并发进行，其关键在于使用了锁分离技术
     * 有些方法需要跨段，比如size()和containsValue()，它们可能需要锁定整个表而而不仅仅是某个段，这需要按顺序锁定所有段，操作完毕后，
     * 又按顺序释放所有段的锁。
     */
    private static final ConcurrentMap<String, BeanCopier> concurrentHashMap = new ConcurrentHashMap<>();
    // private static final Map<String,BeanCopier>map=new HashMap<>();

    /**
     * 全局加载一次
     * 采用单例模式
     * <p>
     * cglib的BeanCopier功能很强大，不过频繁的create太占用资源，降低服务器性能，所以写了下面的代码进行优化。
     * 以达到提升性能的目的。主要就是用缓存将类型相同的copier缓存起来，后续copy的时候就不用再继续创建了。
     *
     * @param clazzSource
     * @param clazzTarget
     * @param useConverter
     * @return
     */
    public BeanCopier create(Class clazzSource, Class clazzTarget, boolean useConverter) {
        String key = clazzSource.getName() + "-" + clazzTarget.getName() + "-" + String.valueOf(useConverter);
        if (concurrentHashMap.containsKey(key)) {
            return concurrentHashMap.get(key);
        }

        BeanCopier beanCopier = BeanCopier.create(clazzSource, clazzTarget, useConverter);
        concurrentHashMap.put(key, beanCopier);
        return beanCopier;
    }


    /**
     * 列表拷贝
     *
     * @param sourceList
     * @param sourceClass
     * @param targetClass
     * @param <T>
     * @param <E>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T, E> List<E> copyList(List<T> sourceList, Class<T> sourceClass, Class<E> targetClass) throws UtilException {
        try {
            BeanCopier bc = this.create(sourceClass, targetClass, true);
            E e = targetClass.newInstance();
            return sourceList.stream().map(s -> {
                this.copy(s, e, bc);
                return e;
            }).collect(Collectors.toList());
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("【{}】{}：{}", UtilEnum.UTIL_1005.toString(), UtilEnum.UTIL_1005.getMessage(), e.getMessage(), e);
            throw new UtilException(UtilEnum.UTIL_1005.toString(), UtilEnum.UTIL_1005.getMessage());
        }
    }


    /**
     * 对象拷贝
     *
     * @param source
     * @param target
     * @param beanCopier
     * @param converter  转换器（为null的时候，只拷贝属性类型和属性名称一致的字段，也可以传入自定义转换器）
     * @param <T>
     * @param <E>
     */
    public <T, E> void copy(T source, E target, BeanCopier beanCopier, Converter converter) {
        beanCopier.copy(source, target, converter);
    }

    /**
     * 对象拷贝，自己实现了一个基本类型转String 类型的转换器
     *
     * @param source
     * @param target
     * @param beanCopier
     * @param <T>
     * @param <E>
     */
    public <T, E> void copy(T source, E target, BeanCopier beanCopier) {
        beanCopier.copy(source, target, new MyBaseConverter());
    }


    /**
     * 对象拷贝，自己实现了一个基本类型转String 类型的转换器
     * @param source
     * @param target
     * @param beanCopier
     * @param isParseString  是否需要把字符串转为其他格式
     * @param <T>
     * @param <E>
     */
    public <T, E> void copy(T source, E target, BeanCopier beanCopier,boolean isParseString) {
        if(isParseString){

            beanCopier.copy(source, target, new MyBaseConverterParseString());
        }else{
            beanCopier.copy(source, target, new MyBaseConverter());
        }
    }

    class MyBaseConverter implements Converter {
        @Override
        public Object convert(Object sPValue, Class tPClass, Object methodName) {
            //Object sPValue 传入的值为基本类型的时候，再获取class时会转为包装类型
            if(null != sPValue && tPClass.equals(String.class)){
                if(String.class.equals(sPValue.getClass())){
                    return sPValue;
                }

                try {
                    // 找到策略模式实现类
                    Class<?> clazz = Class.forName("com.wxm.util.my.strategy." + sPValue.getClass().getSimpleName() + "Strategy");
                    Method method = clazz.getDeclaredMethod("transToString", sPValue.getClass());
                    method.setAccessible(true);
                    Object obj=method.invoke(clazz.newInstance(),sPValue);
                    method.setAccessible(false);
                    if(obj!=null){
                        return obj;
                    }
                    return sPValue;
                } catch (ClassNotFoundException | NoSuchMethodException e) {
                    return sPValue;
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    return sPValue;
                }
            }

            return sPValue;
        }
    }

    class MyBaseConverterParseString implements Converter {
        @Override
        public Object convert(Object sPValue, Class tPClass, Object methodName) {
            if (null != sPValue&&sPValue.getClass().equals(String.class)) {
                // 非空字符串才转
                try {
                    // 找到策略模式实现类
                    Class<?> clazz = Class.forName("com.wxm.util.my.strategy." + tPClass.getSimpleName() + "Strategy");
                    Method method = clazz.getDeclaredMethod("parseToObject", String.class);
                    method.setAccessible(true);
                    Object obj=method.invoke(clazz.newInstance(), (String)sPValue);
                    method.setAccessible(false);
                    return obj;
                } catch (ClassNotFoundException | NoSuchMethodException e) {
                    return sPValue;
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
            return sPValue;
        }
    }

    public ConcurrentMap<String, BeanCopier> get() {
        return concurrentHashMap;
    }



    public static void main(String [] args) throws ParseException {
        Boolean b=false;
        boolean fB=true;
        Object o=false;
        Object i=1;
        log.info("{},{},{},{}",b.toString(),String.valueOf(b),String.valueOf(fB),o.getClass().getName());
        log.info(i.getClass()+"");


        String d=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        log.info("时间：{}",d);
        Date dd=new SimpleDateFormat("yyyy-MM-dd").parse(d);

        String ds=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dd);
        log.info("时间：{}",ds);
    }

}
