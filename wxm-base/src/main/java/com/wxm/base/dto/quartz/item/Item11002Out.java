package com.wxm.base.dto.quartz.item;

import lombok.Data;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/7/4 11:30
 * @since 1.0.0
 */
@Data
public class Item11002Out implements java.io.Serializable{
    /**
     * 调度名称
     */
    private String schedName;

    /**
     * 集群中job的名称
     */
    private String jobName;

    /**
     * 集群中job的所属组的名称
     */
    private String jobGroup;


    /**
     * 集群中job实现类的全名，quartz就是根据这个路径到classpath找到该job类
     */
    private String jobClassName;
    /**
     * 触发器的名称
     */
    private String triggerName;

    /**
     * 触发器所属组的名称
     */
    private String triggerGroup;
    /**
     * cron表达式
     */
    private String cronExpression;

    /**
     * 时区
     */
    private String timeZoneId;
    /**
     * 重复的间隔时间
     */
    private String repeatInterval;
}
