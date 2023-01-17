package com.wxm.base.dto.rbac.in;

import com.wxm.base.annotation.validate.CollectionValidate;
import com.wxm.base.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
/**
 * <p>用户组管理</p>
 * <p>添加用户</p>
 *
 * @author 王森明
 * @date 2022/6/13 11:00
 * @since 1.0.0
 */
@ApiModel("给用户组添加用户")
@Data
public class Rbac22002In extends BaseDto implements java.io.Serializable {

    @ApiModelProperty("用户组ID")
    @NotBlank(message = "用户组ID不能为空")
    private String groupId;

    @ApiModelProperty("用户ID列表")
    @CollectionValidate(message = "用户ID列表不能为空")
    private List<String>list=new ArrayList<>();

}
