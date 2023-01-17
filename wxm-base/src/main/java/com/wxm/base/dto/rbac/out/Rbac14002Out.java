package com.wxm.base.dto.rbac.out;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 用户新增 </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("批量删除用户")
@Data
public class Rbac14002Out  implements java.io.Serializable {
    @ApiModelProperty(value="用户ID列表")
    private List<String> list=new ArrayList<>();
}
