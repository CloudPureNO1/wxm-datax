package com.wxm.druid.entity.master.oauth2;

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
 * oauth_client_details 关联 wxm_c_role
 * </p>
 *
 * @author 王森明
 * @since 2022-06-27
 */
@Getter
@Setter
@TableName("wxm_client_role")
public class WxmClientRole {

    /**
     * 主键
     */
    @TableId(value = "client_role_id", type = IdType.AUTO)
    private Long clientRoleId;

    /**
     * oauth_client_details，客户端ID
     */
    @TableField("client_id")
    private String clientId;

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
