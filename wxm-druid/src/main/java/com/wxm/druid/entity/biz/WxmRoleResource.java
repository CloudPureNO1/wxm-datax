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
 * 角色-资源关联表
 * </p>
 *
 * @author 王森明
 * @since 2022-06-17
 */
@Getter
@Setter
@TableName("wxm_role_resource")
public class WxmRoleResource {

    /**
     * 主键
     */
    @TableId(value = "role_resource_id", type = IdType.INPUT)
    private String roleResourceId;

    /**
     * 角色主键
     */
    @TableField("role_id")
    private String roleId;

    /**
     * 资源主键
     */
    @TableField("resource_id")
    private String resourceId;

    /**
     * 创建者
     */
    @TableField("creator")
    private String creator;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


}
