package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BasePageDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/10/21 10:36
 * @since 1.0.0
 */
@ApiModel("获取所有权限和已经授权的权限")
@Data
public class Rbac51005In extends BasePageDto implements java.io.Serializable{
    private String roleId;
    private String permissionId;
    private String permissionName;
    private String permissionCode;
}
