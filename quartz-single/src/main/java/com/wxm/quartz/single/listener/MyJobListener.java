package com.wxm.quartz.single.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.listeners.JobListenerSupport;
import org.springframework.stereotype.Component;

/**
 * <p>创建任务执行监听器</p>
 * <p>非必须</p>
 *
 * @author 王森明
 * @date 2022/4/28 13:30
 * @since 1.0.0
 */
@Component
public class MyJobListener extends JobListenerSupport {
    @Override
    public String getName() {
        return "myJobListener";
    }

    /**
     * 任务被调度前
     * @param context
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("simpleJobListener监听器，准备执行："+context.getJobDetail().getKey());
    }

    /**
     * 任务调度被拒了
     * @param context
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("simpleJobListener监听器，取消执行："+context.getJobDetail().getKey());
    }

    /**
     * 任务被调度后
     * @param context
     * @param jobException
     */
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("simpleJobListener监听器，执行结束："+context.getJobDetail().getKey());
    }
}
