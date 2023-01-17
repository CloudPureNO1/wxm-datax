package com.wxm.dao.model.ds2;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * qrtz_paused_trigger_grps表 用来存储已暂停的Trigger组的信息。
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@Getter
@Setter
@TableName("qrtz_paused_trigger_grps")
public class PausedTriggerGrps {

    /**
     * 调度名称
     */
    @TableField(value = "SCHED_NAME")
    @MppMultiId
    private String schedName;

    /**
     * 触发器所属组的名称，qrtz_triggers表的TRIGGER_GROUP的外键
     */
    @TableField(value = "TRIGGER_GROUP")
    @MppMultiId
    private String triggerGroup;


}
