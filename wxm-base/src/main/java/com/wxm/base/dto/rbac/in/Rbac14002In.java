package com.wxm.base.dto.rbac.in;

import com.wxm.base.annotation.validate.CollectionValidate;
import com.wxm.base.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> 用户新增 </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@ApiModel("批量删除用户")
@Data
public class Rbac14002In extends BaseDto implements java.io.Serializable {
    // @NotEmpty(message = "用户列表不能为空") 不能校验[null] 和[' '] ['']
    @CollectionValidate(message = "用户ID列表不能为空")
    @ApiModelProperty(value="用户ID列表")
    private List<String> list=new ArrayList<>();
}
