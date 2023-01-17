package com.wxm.druid.entity.master;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色api 关联表
 * </p>
 *
 * @author 王森明
 * @since 2022-06-27
 */
@Getter
@Setter
@TableName("wxm_role_api")
public class WxmRoleApi {

    /**
     * 主键
     */
    @TableId(value = "role_api_id", type = IdType.AUTO)
    private Long roleApiId;

    /**
     * wxm_api 主键
     */
    @TableField("api_id")
    private Long apiId;

    /**
     * wxm_c_role 表的role_id
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 创建者
     */
    @TableField("creator")
    private String creator;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 最近一次操作者
     */
    @TableField("operator")
    private String operator;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
