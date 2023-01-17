package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BaseDto;
import lombok.Data;

import java.util.Date;


/**
 * <p> 用户新增 </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@Data
public class Rbac12001In extends BaseDto implements java.io.Serializable {


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
    private String accountExpiredTime;

    /**
     * 密码过期时间
     */
    private String passwordExpiredTime;

    /**
     * 描述
     */
    private String userDesc;


}
