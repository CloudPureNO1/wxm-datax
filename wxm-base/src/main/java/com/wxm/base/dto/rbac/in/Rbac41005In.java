package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BaseDto;
import com.wxm.base.dto.BasePageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p>资源查询</p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/9 11:55
 * @since 1.0.0
 */
@ApiModel("根据角色Id查询资源列表")
@Data
public class Rbac41005In extends BaseDto implements java.io.Serializable {
    @ApiModelProperty("角色ID")
//    @NotBlank(message = "角色ID不能为空")
    private String roleId;

    private String resourceId;
    private String resourceName;
    private String resourceUrl;
    private String resourceType;
}
