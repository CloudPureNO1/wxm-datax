package com.wxm.base.dto.rbac.out.item;

import lombok.Data;

import java.util.Date;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/13 11:15
 * @since 1.0.0
 */
@Data
public class Item31002Out implements java.io.Serializable
{
    /**
     * 主键
     */
    private String roleId;

    /**
     * 角色唯一编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色类型：0 超级管理员角色，1 系统管理员角色，2 系统管理员角色，3 业务员角色，4 普通角色
     */
    private String roleType;

    /**
     * 0无效 1有效
     */
    private String roleStatus;

    /**
     * 描述
     */
    private String roleDesc;

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

}
