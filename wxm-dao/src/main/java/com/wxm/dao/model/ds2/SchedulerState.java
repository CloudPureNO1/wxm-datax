package com.wxm.dao.model.ds2;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@Getter
@Setter
@TableName("qrtz_scheduler_state")
public class SchedulerState {

    /**
     * 调度名称
     */
    @TableField(value = "SCHED_NAME")
    @MppMultiId
    private String schedName;

    /**
     * 调度实例id，配置文件中org.quartz.scheduler.instanceId配置的名字，就会写入该字段
     */
    @TableField(value = "INSTANCE_NAME")
    @MppMultiId
    private String instanceName;

    /**
     * 上次检查时间
     */
    @TableField("LAST_CHECKIN_TIME")
    private Long lastCheckinTime;

    /**
     * 检查间隔时间
     */
    @TableField("CHECKIN_INTERVAL")
    private Long checkinInterval;


}
