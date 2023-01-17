package com.wxm.base.dto.rbac.out;

import lombok.Data;

import java.util.Date;


/**
 * <p> 用户新增 </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */

@Data
public class Rbac12001Out implements java.io.Serializable {

    /**
     * 主键
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String passwd;

    /**
     * 用户类别：0 超级管理员，1 系统管理员，2 系统管理员，3 业务员，4 普通用户 
     */
    private String userType;

    /**
     * 用户级别：5 普通会员，6白银会员 7 黄金会员 8 白金会员 9 砖石会员
     */
    private String userRate;

    /**
     * 0 无效，1 正常，2 注销，3锁定
     */
    private String userStatus;

    /**
     * 账号过期时间
     */
    private Date accountExpiredTime;

    /**
     * 密码过期时间
     */
    private Date passwordExpiredTime;

    /**
     * 账号锁定时间
     */
    private Date lockTime;

    /**
     * 账号解锁时间
     */
    private Date unlockTime;

    /**
     * 描述
     */
    private String userDesc;

    /**
     * 最近一次操作者
     */
    private String operator;

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
