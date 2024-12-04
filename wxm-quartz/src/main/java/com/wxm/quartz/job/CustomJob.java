package com.wxm.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

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
public class CustomJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        log.info(">>>>>>>>>>>>>>普通定时任务开始>>>>>>>>>>>>>>>>>>>>>>>");
        // 内容
        log.info(">>>>>>>>>>>>>>普通定时任务结束>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
