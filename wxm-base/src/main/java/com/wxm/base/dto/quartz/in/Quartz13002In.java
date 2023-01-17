package com.wxm.base.dto.quartz.in;

import com.wxm.base.bean.QuartzForm;
import com.wxm.base.bean.datax.DataXDbSource;
import com.wxm.base.bean.datax.DataXDbTarget;
import com.wxm.base.bean.datax.DataXErrorLimit;
import com.wxm.base.bean.datax.DataXSpeed;
import com.wxm.base.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * <p>
 * </p>
 *
 * @author 王森明
 * @date 2022/7/7 15:54
 * @since 1.0.0
 */
@ApiModel("普通定时任务修改")
@Data
public class Quartz13002In extends BaseDto implements java.io.Serializable {
    @ApiModelProperty(value = "定时任务Cron表达式",dataType = "String" ,required = true)
    @NotEmpty(message = "定时任务Cron表达式不能为空")
    private String cron;

    @ApiModelProperty(value = "定时任务类",dataType = "String" ,required = true)
    @NotEmpty(message = "定时任务类不能为空")
    private String className;

    @ApiModelProperty(value = "自定义内容实现类",dataType = "String" ,required = false)
    @NotEmpty(message = "自定义内容实现类不能为空")
    private String customClassName;

    @Valid
    @ApiModelProperty(value = "触发器信息",dataType = "JSONString" ,required = true)
    @NotNull(message = "触发器信息不能为空")
    private TriggersIn triggersIn;

    @ApiModel("触发器信息")
    @Data
    public static class TriggersIn{
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
}
