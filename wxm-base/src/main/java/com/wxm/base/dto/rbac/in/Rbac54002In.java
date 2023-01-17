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
 * @date 2022/7/21 9:37
 * @since 1.0.0
 */
@ApiModel("权限批量删除")
@Data
public class Rbac54002In extends BaseDto implements java.io.Serializable {
    @CollectionValidate(message = "权限ID列表不能为空")
    @ApiModelProperty("权限ID列表")
    private List<String>list = new ArrayList<>();
}
