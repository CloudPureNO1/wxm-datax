package com.wxm.service.biz;

import com.wxm.base.enums.BizSvcEnum;
import com.wxm.base.exception.BizSvcException;
import com.wxm.base.exception.DbSvcException;
import com.wxm.util.my.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/9 14:02
 * @since 1.0.0
 */
@Slf4j
public abstract class TemplatePage<T, E> {
    public abstract List<?> queryList(E e) throws DbSvcException;

    public final List<T> service(E e, Class<?> clazzSource, Class<T> clazzTarget, String msg) throws DbSvcException, BizSvcException {
        try {
            List<?> list = this.queryList(e);
            if (CollectionUtils.isEmpty(list)) {
                return new ArrayList<>();
            }
            // 单例模式
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(clazzSource, clazzTarget, true);
            return list.stream().map(s -> {
                T t = null;
                try {
                    t = clazzTarget.newInstance();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                instance.copy(s, t, bc);
                return t;
            }).collect(Collectors.toList());
        } catch (Exception ex) {
            if (e instanceof DbSvcException) {
                throw (DbSvcException) ex;
            }
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage(msg), ex.getMessage(), ex);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage(msg), ex.getMessage());
        }
    }

    public final List<T> service(E e, Class<?> clazzSource, Class<T> clazzTarget, boolean useConverter, Converter converter, String msg) throws DbSvcException, BizSvcException {
        try {
            List<?> list = this.queryList(e);
            // 单例模式
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(clazzSource, clazzTarget, useConverter);
            T t = clazzTarget.newInstance();
            return list.stream().map(s -> {
                if (!useConverter) {
                    instance.copy(s, t, bc, null); // 不适用Converter ，只拷贝属性和名称一致的字段
                }
                if (useConverter && null == converter) {
                    instance.copy(s, t, bc); // 采用自定义默认Converter
                } else if (useConverter && null != converter) {
                    instance.copy(s, t, bc, converter); //使用自定义Converter
                }
                return t;
            }).collect(Collectors.toList());
        } catch (Exception ex) {
            if (e instanceof DbSvcException) {
                throw (DbSvcException) ex;
            }
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage(msg), ex.getMessage(), ex);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage(msg), ex.getMessage());
        }
    }

}
