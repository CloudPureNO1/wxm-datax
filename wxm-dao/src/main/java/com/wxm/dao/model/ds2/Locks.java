package com.wxm.dao.model.ds2;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * qrtz_locks表 用来存储程序的悲观锁的信息(假如使用了悲观锁)。
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@Getter
@Setter
@TableName("qrtz_locks")
public class Locks {

    /**
     * 调度名称
     */
    @TableField(value = "SCHED_NAME")
    @MppMultiId
    private String schedName;

    /**
     * 悲观锁名称
     */
    @TableField(value = "LOCK_NAME")
    @MppMultiId
    private String lockName;


}
