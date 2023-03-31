package com.wxm.druid.entity.quartz;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * qrtz_fired_triggers表 用来存储已触发的Trigger相关的状态信息，以及相关联Job的执行信息。
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@Getter
@Setter
@TableName("QRTZ_FIRED_TRIGGERS")
public class FiredTriggers {

    /**
     * 调度名称
     */
    @TableField(value = "SCHED_NAME")
    @MppMultiId
    private String schedName;

    /**
     * 调度器实例id
     */
    @TableField(value = "ENTRY_ID")
    @MppMultiId
    private String entryId;

    /**
     * 触发器的名称，qrtz_triggers表的TRIGGER_NAME的外键
     */
    @TableField("TRIGGER_NAME")
    private String triggerName;

    /**
     * 触发器所属组的名称，qrtz_triggers表的TRIGGER_GROUP的外键
     */
    @TableField("TRIGGER_GROUP")
    private String triggerGroup;

    /**
     * 调度器实例名
     */
    @TableField("INSTANCE_NAME")
    private String instanceName;

    /**
     * 触发的时间
     */
    @TableField("FIRED_TIME")
    private Long firedTime;

    /**
     * 定时器制定的时间
     */
    @TableField("SCHED_TIME")
    private Long schedTime;

    /**
     * 优先级
     */
    @TableField("PRIORITY")
    private Integer priority;

    /**
     * 状态
     */
    @TableField("STATE")
    private String state;

    /**
     * 集群中job的名称
     */
    @TableField("JOB_NAME")
    private String jobName;

    /**
     * 集群中job的所属组的名称
     */
    @TableField("JOB_GROUP")
    private String jobGroup;

    /**
     * 是否并发
     */
    @TableField("IS_NONCONCURRENT")
    private String isNonconcurrent;

    /**
     * 是否接受恢复执行，默认为false，设置了RequestsRecovery为true，则会被重新执行
     */
    @TableField("REQUESTS_RECOVERY")
    private String requestsRecovery;


}
