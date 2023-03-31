package com.wxm.druid.entity.quartz;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <p>
 * qrtz_simprop_triggers表 用来存储存储CalendarIntervalTrigger和DailyTimeIntervalTrigger。
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@Getter
@Setter
@TableName("QRTZ_SIMPROP_TRIGGERS")
public class SimpropTriggers {

    /**
     * 调度名称
     */
    @TableField(value = "SCHED_NAME")
    @MppMultiId
    private String schedName;

    /**
     * qrtz_triggers表trigger_ name的外键
     */
    @TableField(value = "TRIGGER_NAME")
    @MppMultiId
    private String triggerName;

    /**
     * qrtz_triggers表trigger_group的外键
     */
    @TableField(value = "TRIGGER_GROUP")
    @MppMultiId
    private String triggerGroup;

    /**
     * String类型的trigger的第一个参数
     */
    @TableField("STR_PROP_1")
    private String strProp1;

    /**
     * String类型的trigger的第二个参数
     */
    @TableField("STR_PROP_2")
    private String strProp2;

    /**
     * String类型的trigger的第三个参数
     */
    @TableField("STR_PROP_3")
    private String strProp3;

    /**
     * int类型的trigger的第一个参数
     */
    @TableField("INT_PROP_1")
    private Integer intProp1;

    /**
     * long类型的trigger的第二个参数
     */
    @TableField("INT_PROP_2")
    private Integer intProp2;

    /**
     * long类型的trigger的第一个参数
     */
    @TableField("LONG_PROP_1")
    private Long longProp1;

    /**
     * long类型的trigger的第二个参数
     */
    @TableField("LONG_PROP_2")
    private Long longProp2;

    /**
     * decimal类型的trigger的第一个参数
     */
    @TableField("DEC_PROP_1")
    private BigDecimal decProp1;

    /**
     * decimal类型的trigger的第二个参数
     */
    @TableField("DEC_PROP_2")
    private BigDecimal decProp2;

    /**
     * Boolean类型的trigger的第一个参数
     */
    @TableField("BOOL_PROP_1")
    private String boolProp1;

    /**
     * Boolean类型的trigger的第二个参数
     */
    @TableField("BOOL_PROP_2")
    private String boolProp2;


}
