package com.wxm.druid.entity.quartz;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * qrtz_simple_triggers表 用来存储简单的 Trigger，包括重复次数，间隔，以及已触发的次数
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@Getter
@Setter
@TableName("qrtz_simple_triggers")
public class SimpleTriggers {

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
     * 重复的次数统计
     */
    @TableField("REPEAT_COUNT")
    private Long repeatCount;

    /**
     * 重复的间隔时间
     */
    @TableField("REPEAT_INTERVAL")
    private Long repeatInterval;

    /**
     * 已经触发的次数
     */
    @TableField("TIMES_TRIGGERED")
    private Long timesTriggered;


}
