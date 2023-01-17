package com.wxm.base.dto.rbac.out;

import lombok.Data;

import java.util.Date;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/17 10:04
 * @since 1.0.0
 */
@Data
public class Rbac52001Out implements java.io.Serializable{
    /**
     * 主键
     */
    private String permissionId;
    private String permissionStatus;
    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 描述
     */
    private String permissionDesc;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 权限url
     */
    private String permissionUrl;
    private String resourceId;
    private String permissionType;
}
