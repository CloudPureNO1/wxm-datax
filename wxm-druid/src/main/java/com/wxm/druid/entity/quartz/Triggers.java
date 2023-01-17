package com.wxm.druid.entity.quartz;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

/**
 * <p>
 * 触发器表
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@Getter
@Setter
@TableName("qrtz_triggers")
public class Triggers {

    /**
     * 调度名称
     */
    @TableField(value = "SCHED_NAME")
    @MppMultiId
    private String schedName;

    /**
     * 触发器的名称
     */
    @TableField(value = "TRIGGER_NAME")
    @MppMultiId
    private String triggerName;

    /**
     * 触发器所属组的名称
     */
    @TableField(value = "TRIGGER_GROUP")
    @MppMultiId
    private String triggerGroup;

    /**
     * qrtz_job_details表JOB_NAME的外键
     */
    @TableField("JOB_NAME")
    private String jobName;

    /**
     * qrtz_job_details表JOB_GROUP的外键
     */
    @TableField("JOB_GROUP")
    private String jobGroup;

    /**
     * 详细描述信息
     */
    @TableField("`DESCRIPTION`")
    private String description;

    /**
     * 下一次触发时间（毫秒），默认为-1，意味不会自动触发
     */
    @TableField("NEXT_FIRE_TIME")
    private Long nextFireTime;

    /**
     * 上一次触发时间（毫秒）
     */
    @TableField("PREV_FIRE_TIME")
    private Long prevFireTime;

    /**
     * 优先级
     */
    @TableField("PRIORITY")
    private Integer priority;

    /**
     * 当前触发器状态（ WAITING：等待； PAUSED：暂停； ACQUIRED：正常执行； BLOCKED：阻塞； ERROR：错误；）
     */
    @TableField("TRIGGER_STATE")
    private String triggerState;

    /**
     * 触发器的类型，使用cron表达式
     */
    @TableField("TRIGGER_TYPE")
    private String triggerType;

    /**
     * 开始时间
     */
    @TableField("START_TIME")
    private Long startTime;

    /**
     * 结束时间
     */
    @TableField("END_TIME")
    private Long endTime;

    /**
     * 日程表名称，表qrtz_calendars的CALENDAR_NAME字段的值
     */
    @TableField("CALENDAR_NAME")
    private String calendarName;

    /**
     * 措施或者是补偿执行的策略
     */
    @TableField("MISFIRE_INSTR")
    private Integer misfireInstr;

    /**
     * 一个blob字段，存放持久化job对象
     */
    @TableField("JOB_DATA")
    private Blob jobData;


}
