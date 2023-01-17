package com.wxm.base.dto.rbac.out.item;

import lombok.Data;

import java.util.Date;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/17 10:09
 * @since 1.0.0
 */
@Data
public class Item51002Out implements java.io.Serializable{
    /**
     * 主键
     */
    private String permissionId;

    /**
     * 权限编码
     */
    private String permissionCode;
    private String permissionStatus;
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
     * 操作者
     */
    private String operator;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;


    private String permissionType;
}
