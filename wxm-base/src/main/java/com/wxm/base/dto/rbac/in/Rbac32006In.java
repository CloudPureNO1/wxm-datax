package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/10/21 15:13
 * @since 1.0.0
 */
@ApiModel("新增、编辑 角色的接口")
@Data
public class Rbac32006In extends BaseDto implements java.io.Serializable {
    /**
     * 角色ID
     */
    @NotBlank(message = "角色Id不能为空")
    private String roleId;
    /**
     * 接口ID列表
     */
    private List<String> list=new ArrayList<>();

}
