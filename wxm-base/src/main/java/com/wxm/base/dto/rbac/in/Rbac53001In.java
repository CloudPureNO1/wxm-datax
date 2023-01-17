package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BaseDto;
import lombok.Data;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/17 10:02
 * @since 1.0.0
 */
@Data
public class Rbac53001In extends BaseDto implements java.io.Serializable {
    /**
     * 主键
     */
    private String permissionId;

    private String permissionStatus;
    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 描述
     */
    private String permissionDesc;


    private String permissionType;
    /**
     * 权限编码
     */
    private String permissionCode;
}
