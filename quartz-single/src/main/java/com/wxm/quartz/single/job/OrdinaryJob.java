package com.wxm.quartz.single.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/7/11 14:03
 * @since 1.0.0
 */
@Slf4j
@Component
public class OrdinaryJob implements Job {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        log.info(">>>>>>>>>>>>>>普通定时任务开始>>>>>>>>>>>>>>>>>>>>>>>");
        try {
            // 有这一行代码，才能取到传入的数据
            SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
            String params = context.getJobDetail().getJobDataMap().getString("params");
            String customClass = context.getJobDetail().getJobDataMap().getString("customClass");
            if (StringUtils.hasLength(customClass)) {
                Class clazz=Class.forName(customClass);
                OrdinaryAbstract obj = (OrdinaryAbstract) applicationContext.getBean(clazz);
                obj.execute(params);
                    /*
                      不采用反射，直接采用抽象类
                                Object obj=applicationContext.getBean(clazz);
                                Method mtd=clazz.getDeclaredMethod("execute");
                                mtd.setAccessible(true);
                                mtd.invoke(obj,null);
                                mtd.setAccessible(false);*/

            }

        } catch (ClassNotFoundException e) {
            log.info("普通定时任务执行发生异常：" + e.getMessage(), e);
            throw new RuntimeException("普通定时任务执行发生异常：" + e.getMessage());
        } catch (Exception e) {
            log.info("普通定时任务执行发生异常：" + e.getMessage(), e);
            throw new RuntimeException("普通定时任务执行发生异常：" + e.getMessage());
        }
        log.info(">>>>>>>>>>>>>>普通定时任务结束>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
