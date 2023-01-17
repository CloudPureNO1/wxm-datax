package com.wxm.base.dto.quartz.in;

import com.wxm.base.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/7/8 11:46
 * @since 1.0.0
 */
@Data
public class Quartz11004In extends BaseDto implements java.io.Serializable {
    @NotBlank(message = "调度器名称不能为空")
    private String schedName;
    @NotBlank(message = "触发器名称不能未开工")
    private String triggerName;
    @NotBlank(message = "触发器组不能为空")
    private String triggerGroup;
}
