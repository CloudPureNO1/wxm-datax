package com.wxm.base.dto.rbac.out;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/7/21 9:47
 * @since 1.0.0
 */
@ApiModel("接口批量删除")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rbac74002Out   implements java.io.Serializable {
    @ApiModelProperty("接口ID列表")
    private List<String>list=new ArrayList();
}
