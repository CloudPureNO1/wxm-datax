package com.wxm.base.dto.rbac.out;

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
public class Rbac24004Out   implements java.io.Serializable {
    @ApiModelProperty("用户组ID列表")
    private List<String>list=new ArrayList<>();
}
