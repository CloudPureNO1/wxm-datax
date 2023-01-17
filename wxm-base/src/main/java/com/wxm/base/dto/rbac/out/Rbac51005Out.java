package com.wxm.base.dto.rbac.out;

import com.wxm.base.dto.rbac.out.item.Item51005Out;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/10/21 10:37
 * @since 1.0.0
 */
@Data
public class Rbac51005Out implements java.io.Serializable{
    private long total;
    @ApiModelProperty("权限列表")
    private List<Item51005Out>list=new ArrayList<>();
}
