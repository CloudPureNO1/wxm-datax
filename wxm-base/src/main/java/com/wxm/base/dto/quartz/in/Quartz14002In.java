package com.wxm.base.dto.quartz.in;

import com.wxm.base.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/7/8 11:46
 * @since 1.0.0
 */
@ApiModel("普通定时任务删除")
@Data
public class Quartz14002In extends BaseDto implements java.io.Serializable {
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
}
