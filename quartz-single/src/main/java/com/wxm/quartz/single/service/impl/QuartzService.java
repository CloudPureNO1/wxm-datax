package com.wxm.quartz.single.service.impl;


import com.wxm.base.bean.JobBean;
import com.wxm.base.enums.JobEnum;
import com.wxm.base.exception.JobException;
import com.wxm.base.exception.UtilException;
import com.wxm.datax.config.DataXPropertiesConfig;
import com.wxm.quartz.single.service.IQuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Date;


/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/4/28 14:56
 * @since 1.0.0
 */
@Slf4j
@Service("quartzService")
public class QuartzService implements IQuartzService {
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private DataXPropertiesConfig dataXPropertiesConfig;


    @Override
    public void addJob(Class clazz, String jobName, String jobGroup, String triggerName, String triggerGroup, String cron, String jobDescription, String triggerDestination) throws JobException {
        try {
            //Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            JobDetail job = JobBuilder.newJob(clazz)
                    .withIdentity(jobName, jobGroup)
                    .withDescription(jobDescription)
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerName, triggerGroup)
                    .withDescription(triggerDestination)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .startNow()
                    .build();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (Exception e) {
            log.info("【{}】{}:【jobName={},jobGroup={}】：{}", JobEnum.JOB_1001.toString(), JobEnum.JOB_1001.getMessage(), jobName, jobGroup, e.getMessage(), e);
            throw new JobException(JobEnum.JOB_1001.toString(), e.getMessage());
        }

    }

    @Override
    public void addJob(Class clazz, JobBean jobBean, String jobFilePath) throws JobException {
        try {
            //Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDataMap dataMap = new JobDataMap();
            dataMap.put("jobFilePath", jobFilePath);
            JobDetail job = JobBuilder.newJob(clazz)
                    .withIdentity(jobBean.getJobName(), jobBean.getJobGroup())
                    .withDescription(jobBean.getJobDescription())
                    .setJobData(dataMap)
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobBean.getTriggerName(), jobBean.getTriggerGroup())
                    .withDescription(jobBean.getTriggerDescription())
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobBean.getCron()))
                    .startNow()
                    .build();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (Exception e) {
            log.info("【{}】{}:【jobName={},jobGroup={}】：{}", JobEnum.JOB_1001.toString(), JobEnum.JOB_1001.getMessage(), jobBean.getJobName(), jobBean.getJobName(), e.getMessage(), e);
            throw new JobException(JobEnum.JOB_1001.toString(), e.getMessage());
        }
    }

    @Override
    public void addJob(Class jobClazz, JobBean jobBean, String customClass, String params) throws IOException, UtilException, JobException {
        try {
            //Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDataMap dataMap = new JobDataMap();
            if (StringUtils.hasLength(customClass)) {
                dataMap.put("customClass", customClass);
            }
            if (StringUtils.hasLength(params)) {
                dataMap.put("params", params);
            }

            JobDetail job = JobBuilder.newJob(jobClazz)
                    .withIdentity(jobBean.getJobName(), jobBean.getJobGroup())
                    .withDescription(jobBean.getJobDescription())
                    .setJobData(dataMap)
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobBean.getTriggerName(), jobBean.getTriggerGroup())
                    .withDescription(jobBean.getTriggerDescription())
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobBean.getCron()))
                    .startNow()
                    .build();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (Exception e) {
            log.info("【{}】{}:【jobName={},jobGroup={}】：{}", JobEnum.JOB_1001.toString(), JobEnum.JOB_1001.getMessage(), jobBean.getJobName(), jobBean.getJobName(), e.getMessage(), e);
            throw new JobException(JobEnum.JOB_1001.toString(), e.getMessage());
        }
    }


    @Override
    public void addJob(Class clazz, String jobName, String jobGroup, String triggerName, String triggerGroup, String cron, String jobDescription, String triggerDestination, String jobFilePath) throws JobException {
        try {
            //Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDataMap dataMap = new JobDataMap();
            dataMap.put("jobFilePath", jobFilePath);
            JobDetail job = JobBuilder.newJob(clazz)
                    .withIdentity(jobName, jobGroup)
                    .withDescription(jobDescription)
                    .setJobData(dataMap)
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerName, triggerGroup)
                    .withDescription(triggerDestination)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .startNow()
                    .build();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (Exception e) {
            log.info("【{}】{}:【jobName={},jobGroup={}】：{}", JobEnum.JOB_1001.toString(), JobEnum.JOB_1001.getMessage(), jobName, jobGroup, e.getMessage(), e);
            throw new JobException(JobEnum.JOB_1001.toString(), e.getMessage());
        }

    }

    @Override
    public void pauseJob(String jobName, String jobGroup) throws JobException {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobName, jobGroup));
        } catch (SchedulerException e) {
            log.info("【{}】{}:【jobName={},jobGroup={}】：{}", JobEnum.JOB_1002.toString(), JobEnum.JOB_1002.getMessage(), jobName, jobGroup, e.getMessage(), e);
            throw new JobException(JobEnum.JOB_1002.toString(), e.getMessage());
        }
    }

    @Override
    public void resumeJob(String jobName, String jobGroup) throws JobException {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobName, jobGroup));
        } catch (SchedulerException e) {
            log.info("【{}】{}:【jobName={},jobGroup={}】：{}", JobEnum.JOB_1003.toString(), JobEnum.JOB_1003.getMessage(), jobName, jobGroup, e.getMessage(), e);
            throw new JobException(JobEnum.JOB_1003.toString(), e.getMessage());
        }
    }

    @Override
    public Date rescheduleJob(String triggerName, String triggerGroup, String cron) throws JobException {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
            CronTrigger cronTriggerNew = (CronTrigger) scheduler.getTrigger(triggerKey);
            cronTriggerNew = cronTriggerNew.getTriggerBuilder().withIdentity(triggerKey).withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
            return scheduler.rescheduleJob(triggerKey, cronTriggerNew);
        } catch (SchedulerException e) {
            log.info("【{}】{}:【jobName={},jobGroup={}】：{}", JobEnum.JOB_1004.toString(), JobEnum.JOB_1004.getMessage(), triggerName, triggerGroup, e.getMessage(), e);
            throw new JobException(JobEnum.JOB_1004.toString(), e.getMessage());
        }
    }

    @Override
    public boolean deleteJob(String jobName, String jobGroup) throws JobException {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            return scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
        } catch (SchedulerException e) {
            log.info("【{}】{}:【jobName={},jobGroup={}】：{}", JobEnum.JOB_1005.toString(), JobEnum.JOB_1005.getMessage(), jobName, jobGroup, e.getMessage(), e);
            throw new JobException(JobEnum.JOB_1005.toString(), e.getMessage());
        }
    }
}
