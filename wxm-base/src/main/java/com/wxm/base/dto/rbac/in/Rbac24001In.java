package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/13 11:00
 * @since 1.0.0
 */
@ApiModel("用户组删除")
@Data
public class Rbac24001In extends BaseDto implements java.io.Serializable {
    @NotBlank(message = "用户组ID不能为空")
    @ApiModelProperty("用户组ID")
    private String groupId;

}
