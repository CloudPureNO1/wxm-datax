package com.wxm.base.dto.quartz.in;

import com.wxm.base.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/7/8 11:46
 * @since 1.0.0
 */
@Valid
@ApiModel("Quartz定时任务恢复")
@Data
public class Quartz16001In extends BaseDto implements java.io.Serializable {
    private String schedName;
    private String triggerName;
    private String triggerGroup;
    @NotBlank(message = "定时任务名不能为空")
    private String jobName;
    @NotBlank(message = "定时任务组不能为空")
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
