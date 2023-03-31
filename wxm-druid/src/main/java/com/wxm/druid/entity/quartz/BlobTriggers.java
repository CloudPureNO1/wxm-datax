package com.wxm.druid.entity.quartz;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

/**
 * <p>
 * qrtz_blob_triggers表 用来存储Trigger作为Blob类型(用于 Quartz 用户用 JDBC 创建他们自己定制的 Trigger 类型，JobStore 并不知道如何存储实例的时候)。
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@Getter
@Setter
@TableName("QRTZ_BLOB_TRIGGERS")
public class BlobTriggers {

    /**
     * 调度名称
     */
    @TableField(value = "sched_name")
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
     * 一个blob字段，存放持久化Trigger对象
     */
    @TableField("BLOB_DATA")
    private Blob blobData;


}
