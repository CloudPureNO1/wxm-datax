package com.wxm.dao.model.ds1;

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
 * 用户表
 * </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@Getter
@Setter
@TableName("wxm_user")
public class WxmUser {

    /**
     * 主键
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("passwd")
    private String passwd;

    /**
     * 用户类别：0 超级管理员，1 系统管理员，2 系统管理员，3 业务员，4 普通用户 
     */
    @TableField("user_type")
    private String userType;

    /**
     * 用户级别：5 普通会员，6白银会员 7 黄金会员 8 白金会员 9 砖石会员
     */
    @TableField("user_rate")
    private String userRate;

    /**
     * 0 无效，1 正常，2 注销，3锁定
     */
    @TableField("user_status")
    private String userStatus;

    /**
     * 账号过期时间
     */
    @TableField("account_expired_time")
    private LocalDateTime accountExpiredTime;

    /**
     * 密码过期时间
     */
    @TableField("password_expired_time")
    private LocalDateTime passwordExpiredTime;

    /**
     * 账号锁定时间
     */
    @TableField("lock_time")
    private LocalDateTime lockTime;

    /**
     * 账号解锁时间
     */
    @TableField("unlock_time")
    private LocalDateTime unlockTime;

    /**
     * 描述
     */
    @TableField("user_desc")
    private String userDesc;

    /**
     * 最近一次操作者
     */
    @TableField("operator")
    private String operator;

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
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
