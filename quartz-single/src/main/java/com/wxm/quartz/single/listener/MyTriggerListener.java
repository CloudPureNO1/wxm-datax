package com.wxm.quartz.single.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.listeners.TriggerListenerSupport;
import org.springframework.stereotype.Component;

/**
 * <p>创建任务触发监听器</p>
 * <p>非必须</p>
 *
 * @author 王森明
 * @date 2022/4/28 13:28
 * @since 1.0.0
 */
@Component
public class MyTriggerListener extends TriggerListenerSupport {
    @Override
    public String getName() {
        return "myTriggerListener";
    }

    /**
     * Trigger被激发 它关联的job即将被运行
     * @param trigger
     * @param context
     */
    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        System.out.println("myTriggerListener.triggerFired()");
    }

    /**
     * Trigger被激发 它关联的job即将被运行, TriggerListener 给了一个选择去否决 Job 的执行,如果返回TRUE 那么任务job会被终止
     * @param trigger
     * @param context
     * @return
     */
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        System.out.println("myTriggerListener.vetoJobExecution()");
        return false;
    }

    /**
     * 当Trigger错过被激发时执行,比如当前时间有很多触发器都需要执行，但是线程池中的有效线程都在工作，
     * 那么有的触发器就有可能超时，错过这一轮的触发。
     * @param trigger
     */
    @Override
    public void triggerMisfired(Trigger trigger) {
        System.out.println("myTriggerListener.triggerMisfired()");
    }

    /**
     * 任务完成时触发
     * @param trigger
     * @param context
     * @param triggerInstructionCode
     */
    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        System.out.println("myTriggerListener.triggerComplete()");
    }
}
