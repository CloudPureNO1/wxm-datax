package com.wxm.dao.model.ds2;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * qrtz_cron_triggers表 用来存储触发器 Cron表达式和时区信息。
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@Getter
@Setter
@TableName("qrtz_cron_triggers")
public class CronTriggers {

    /**
     * 调度名称
     */
    @TableField(value = "SCHED_NAME")
    @MppMultiId
    private String schedName;

    /**
     * 触发器的名称，qrtz_triggers表的TRIGGER_NAME的外键
     */
    @TableField(value = "TRIGGER_NAME")
    @MppMultiId
    private String triggerName;

    /**
     * 触发器所属组的名称，qrtz_triggers表的TRIGGER_GROUP的外键
     */
    @TableField(value = "TRIGGER_GROUP")
    @MppMultiId
    private String triggerGroup;

    /**
     * cron表达式
     */
    @TableField("CRON_EXPRESSION")
    private String cronExpression;

    /**
     * 时区
     */
    @TableField("TIME_ZONE_ID")
    private String timeZoneId;


}
