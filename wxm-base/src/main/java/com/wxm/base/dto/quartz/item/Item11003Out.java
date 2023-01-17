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
public class Item11003Out implements java.io.Serializable{

    private String schedName;

    private String triggerName;

    private String triggerGroup;

    private String jobName;

    private String jobGroup;

    private String description;

    private String nextFireTime;

    private String prevFireTime;

    private String priority;

    private String triggerState;

    private String triggerType;

    private String startTime;

    private String endTime;

    private String calendarName;

    private String misfireInstr;

    private String jobData;

    // qrtz_cron_triggers
    private String cronExpression;
    private String timeZoneId;

    private String jobClassName;
}
