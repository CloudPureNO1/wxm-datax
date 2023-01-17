package com.wxm.base.dto.rbac.in;

import com.wxm.base.annotation.validate.CollectionValidate;
import com.wxm.base.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/7/20 14:45
 * @since 1.0.0
 */
@ApiModel("批量删除角色")
@Data
public class Rbac34004In extends BaseDto implements java.io.Serializable {
    @CollectionValidate(message = "角色ID列表不能为空")
    @ApiModelProperty("角色ID列表")
    List<String>list=new ArrayList();
}
