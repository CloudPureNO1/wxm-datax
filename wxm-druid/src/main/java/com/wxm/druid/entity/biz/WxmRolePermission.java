package com.wxm.druid.entity.biz;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色-权限关联表
 * </p>
 *
 * @author 王森明
 * @since 2022-06-17
 */
@Getter
@Setter
@TableName("wxm_role_permission")
public class WxmRolePermission {

    /**
     * 主键
     */
    @TableId(value = "role_permission_id", type = IdType.INPUT)
    private String rolePermissionId;

    /**
     * 角色主键
     */
    @TableField("role_id")
    private String roleId;

    /**
     * 资源主键
     */
    @TableField("permission_id")
    private String permissionId;

    /**
     * 创建者
     */
    @TableField("creator")
    private String creator;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


}
