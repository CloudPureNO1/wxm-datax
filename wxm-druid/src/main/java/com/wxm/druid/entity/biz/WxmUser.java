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
 * 用户表
 * </p>
 *
 * @author wangxm
 * @since 2024-05-22
 */
@Getter
@Setter
@TableName("wxm_user")
public class WxmUser {

    /**
     * 主键
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private String userId;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码，密文
     */
    @TableField("passwd")
    private String passwd;

    /**
     * 用户类别：U-SUPER-ADMIN 超级管理员，U-ADMIN 系统管理员，U-MANAGER 系统管理员，U-BIZ 业务员，U-ORDINARY 普通用户 
     */
    @TableField("user_type")
    private String userType;

    /**
     * 用户级别：NON-MEMBER 普通会员，SILVER-MEMBER 白银会员 GOLD-MEMBER 黄金会员 PLATINUM-MEMBER 白金会员 DIAMOND-MEMBER 钻石会员
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
    private Date accountExpiredTime;

    /**
     * 密码过期时间
     */
    @TableField("password_expired_time")
    private Date passwordExpiredTime;

    /**
     * 账号锁定时间
     */
    @TableField("lock_time")
    private Date lockTime;

    /**
     * 账号解锁时间
     */
    @TableField("unlock_time")
    private Date unlockTime;

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
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 联系电话
     */
    @TableField("phone_number")
    private String phoneNumber;

    /**
     * Facebook ID（社交账号关联）
     */
    @TableField("facebook_id")
    private String facebookId;

    /**
     * 微信OpenID（社交账号关联）
     */
    @TableField("wechat_openid")
    private String wechatOpenid;

    /**
     * 座机
     */
    @TableField("tel")
    private String tel;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 头像URL
     */
    @TableField("avatar_url")
    private String avatarUrl;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 性别
     */
    @TableField("gender")
    private String gender;

    /**
     * 生日
     */
    @TableField("birthday")
    private Date birthday;

    /**
     * 注册IP
     */
    @TableField("registration_ip")
    private String registrationIp;

    /**
     * 注册时间
     */
    @TableField("register_time")
    private Date registerTime;

    /**
     * 最后登录时间
     */
    @TableField("last_login")
    private Date lastLogin;

    /**
     * 用户积分
     */
    @TableField("membership_point")
    private Integer membershipPoint;


}
