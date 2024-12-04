package com.wxm.quartz.service;

import com.wxm.base.bean.JobBean;
import com.wxm.base.exception.JobException;
import com.wxm.base.exception.UtilException;

import java.io.IOException;
import java.util.Date;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/4/28 13:10
 * @since 1.0.0
 */
public interface IQuartzService {

    /**
     * 新增定时任务
     * @param clazz 定时任务类
     * @param jobBean 定时任务参数bean
     * @param jobFileName 定时任务datax job文件名或其他值
     * @throws JobException
     */
    void addJob (Class clazz, JobBean jobBean, String jobFileName) throws IOException,UtilException, JobException;

    /**
     * 新增定时任务
     * @param jobClazz 定时任务类
     * @param jobBean 定时任务参数bean
     * @param customClass 自定义任务类
     * @param params
     * @throws JobException
     */
    void addJob (Class jobClazz, JobBean jobBean,String customClass,String params) throws IOException,UtilException, JobException;

    /**
     * 新增定时任务
     * @param clazz 定时任务类
     * @param jobName 定时任务名
     * @param jobGroup 定时任务所在组
     * @param triggerName 定时任务的触发器名
     * @param triggerGroup 定时任务的触发器所在组
     * @param cron Cron Expressions 定时表达式
     * @param jobDescription 定时任务描述
     * @param triggerDestination 触发器描述
     * @throws JobException
     */
    void addJob (Class clazz, String jobName, String jobGroup, String triggerName, String triggerGroup,String cron,String jobDescription,String triggerDestination,String jobFileName) throws JobException;


    /**
     * 新增定时任务
     * @param clazz 定时任务类
     * @param jobName 定时任务名
     * @param jobGroup 定时任务所在组
     * @param triggerName 定时任务的触发器名
     * @param triggerGroup 定时任务的触发器所在组
     * @param cron Cron Expressions 定时表达式
     * @param jobDescription 定时任务描述
     * @param triggerDestination 触发器描述
     */
    void addJob (Class clazz, String jobName, String jobGroup, String triggerName, String triggerGroup,String cron,String jobDescription,String triggerDestination) throws JobException;

    /**
     * 停止定时任务
     * @param jobName 定时任务名
     * @param jobGroup 定时任务所在组
     */
    void pauseJob(String jobName,String jobGroup) throws JobException;

    /**
     * 恢复（取消暂停） 定时任务
     * @param jobName 定时任务名
     * @param jobGroup 定时任务所在组
     */
    void resumeJob(String jobName,String jobGroup) throws JobException;

    /**
     * 移除（删除）具有给定密钥的触发器，并存储新的给定触发器-该触发器必须与同一作业关联（新触发器必须指定作业名称和组）-但是，新触发器不需要与旧触发器具有相同的名称。
     * @param triggerGroup 定时任务名触发器名称
     * @param triggerGroup 定时任务触发器所在组
     * @param cron Cron Expressions 定时表达式
     * @return
     */
    Date rescheduleJob(String triggerName,String triggerGroup,String cron) throws JobException;

    /**
     * 删除定时任务
     * @param jobName 定时任务名
     * @param jobGroup 定时任务所在组
     * @return true if the Job was found and deleted.
     */
    boolean deleteJob(String jobName,String jobGroup) throws JobException;
}
