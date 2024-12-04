package com.wxm.api.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxm.druid.entity.biz.WxmUser;
import com.wxm.druid.entity.quartz.DataXDetails;
import com.wxm.druid.mapper.biz.WxmUserMapper;
import com.wxm.druid.mapper.quartz.DataXDetailsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/8 9:52
 * @since 1.0.0
 */
@Component
@Slf4j
public class Test {

    @Autowired
    private WxmUserMapper wxmUserMapper;
    @Autowired
    private DataXDetailsMapper dataXDetailsMapper;

    public String test(){
        List<WxmUser> wxmUsers = wxmUserMapper.selectAll();
        log.info(">>>>",wxmUsers);

        List<DataXDetails> dataXDetails = dataXDetailsMapper.selectList(new QueryWrapper<>());
        log.info(">>>>",dataXDetails);

        return "";
    }

    public static void main(String[] args) {
   /*     List<Quartz10001Out> listTarget=new ArrayList<>();
        List<JobDetailAndTriggerBean> listSource = new ArrayList<>();
        JobDetailAndTriggerBean bean=new JobDetailAndTriggerBean();
        bean.setJobName("test").setJobClassName("Test.class").setJobGroup("GTest").setRepeatInterval(10L).setTriggerName("testT");
        listSource.add(bean);
        Quartz10001Out out=new Quartz10001Out();

        // org.springframework.beans.BeanUtils 自带的必须是属性类型和字段都严格一样，才会复制值
        org.springframework.beans.BeanUtils.copyProperties(bean,out);
        log.info(">>>>>>>>>>>>>>>>结果：{}", JSON.toJSONString(out));
        listTarget=JSON.parseArray(JSON.toJSONString(listSource),Quartz10001Out.class);
        log.info(">>>>>>>>>>>>>>>>结果：{}", JSON.toJSONString(listTarget));*/


        Long tL=10L;
        long tl=10L;
        log.info(">>long>>>>{},{}",String.valueOf(tL),String.valueOf(tl));
        Boolean bB=true;
        boolean bb=true;
        log.info(">>>boolean>>>{},{}",String.valueOf(bB),String.valueOf(bb));
        Double dD=10000.1434343;
        double dd=10000.1434343;
        log.info(">>>double>>>{},{}",String.valueOf(dD),String.valueOf(dd));
        BigDecimal b=new BigDecimal(23423423.23423423423);
        BigDecimal bs=new BigDecimal("23423423.23423423423");
        log.info("{},{}",String.valueOf(b),String.valueOf(bs));
        log.info("{},{}",b.toPlainString(),bs.toPlainString());
        log.info("{},{}",b.doubleValue(),bs.doubleValue());

        BigDecimal bd=BigDecimal.valueOf(23423423.23423423423);
        log.info(bd.toString());


        Class clazz=TestBean.class;
        Field [] fileds = clazz.getDeclaredFields();
        for (Field field : fileds) {
           // log.info("{},{},{},{}",field.getName(),field.getDeclaringClass(),field.getClass(),field.getType());
            Class cls = field.getType();
            log.info("{}:{}:{}" ,cls,cls.getName(),cls.getSimpleName());
        }


        int i=11;
        TestBean testBen=new TestBean();
        log.info("i 的 类型为："+testBen.getType(i));

    }

    static class TestBean{
        private int id;
        private Integer idI;
        private double dd;
        private Double ddD;

        public String  getType(Object obj){
            log.info("obj 是否为Inter类型：{}",obj.getClass().equals(Integer.class));
            return obj.getClass().toString()+":"+obj.getClass().getName()+":"+obj.getClass().getSimpleName();
        }
    }
}
