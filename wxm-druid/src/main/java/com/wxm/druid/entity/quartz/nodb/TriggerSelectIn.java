package com.wxm.druid.entity.quartz.nodb;

import lombok.Data;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/7/4 14:52
 * @since 1.0.0
 */
@Data
public class TriggerSelectIn implements java.io.Serializable{
    private String schedName;
    private String triggerName;
    private String triggerGroup;
    private String jobName;
    private String jobGroup;
    // yyyyMMddHHmmss
    private String nextFireTimeStart;
    // yyyyMMddHHmmss
    private String nextFireTimeEnd;
    // yyyyMMddHHmmss
    private String prevFireTimeStart;
    // yyyyMMddHHmmss
    private String prevFireTimeEnd;
    private String priority;
    private String triggerState;
    private String triggerType;
    // yyyyMMddHHmmss
    private String startTimeStart;
    // yyyyMMddHHmmss
    private String startTimeEnd;
    // yyyyMMddHHmmss
    private String endTimeStart;
    // yyyyMMddHHmmss
    private String endTimeEnd;

    private int num=1;
    private String dataXFlag;
}
