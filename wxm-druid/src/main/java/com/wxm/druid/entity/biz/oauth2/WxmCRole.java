package com.wxm.druid.entity.biz.oauth2;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * oauth_client_details 客户端的角色 关联wxm_api
 * </p>
 *
 * @author 王森明
 * @since 2022-06-27
 */
@Getter
@Setter
@TableName("wxm_c_role")
public class WxmCRole {

    /**
     * 主键
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色唯一编码
     */
    @TableField("role_code")
    private String roleCode;

    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;

    /**
     * 角色类型：0 超级管理员角色，1 系统管理员角色，2 系统管理员角色，3 业务员角色，4 普通角色
     */
    @TableField("role_type")
    private String roleType;

    /**
     * 0无效 1有效
     */
    @TableField("role_status")
    private String roleStatus;

    /**
     * 描述
     */
    @TableField("role_desc")
    private String roleDesc;

    /**
     * 创建者
     */
    @TableField("creator")
    private String creator;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 最近一次操作者
     */
    @TableField("operator")
    private String operator;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
