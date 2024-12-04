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
 * 资源的权限表（资源中的按钮和其他功能）
 * </p>
 *
 * @author 王森明
 * @since 2022-06-17
 */
@Getter
@Setter
@TableName("wxm_permission")
public class WxmPermission {

    /**
     * 主键
     */
    @TableId(value = "permission_id", type = IdType.INPUT)
    private String permissionId;

    /**
     * 权限编码
     */
    @TableField("permission_code")
    private String permissionCode;

    /**
     * 权限名称
     */
    @TableField("permission_name")
    private String permissionName;

    /**
     * 1有效 0 无效
     */
    @TableField("permission_status")
    private String permissionStatus;
    /**
     * 描述
     */
    @TableField("permission_desc")
    private String permissionDesc;

    /**
     * 创建者
     */
    @TableField("creator")
    private String creator;
    /**
     * 最近一次操作者
     */
    @TableField("operator")
    private String operator;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;




    @TableField("permission_type")
    private String permissionType;

    @TableField(exist = false)
    private String checked;// 单独属性，没有对应字段
}
