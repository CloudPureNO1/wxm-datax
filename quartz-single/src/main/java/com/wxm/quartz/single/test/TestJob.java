package com.wxm.quartz.single.test;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/4/27 16:24
 * @since 1.0.0
 */
@Slf4j
public class TestJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(">>>>>>>>>>测试job>>>>>>>>>>>>>>");
    }
}
