package com.wxm.base.dto.rbac.in;

import com.wxm.base.annotation.validate.CollectionValidate;
import com.wxm.base.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/7/20 11:41
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel("用户组批量删除")
public class Rbac24004In extends BaseDto implements java.io.Serializable {
    /**
     * 校验list!=null ,list.size()==0 ,而不能校验[null]
     * @NotEmpty(message = "用户组ID列表不能为空")
     */
    @CollectionValidate(message = "用户组ID列表不能为空")
    @ApiModelProperty("用户组ID列表")
    private List<String> list = new ArrayList<>();
}
