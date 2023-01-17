package com.wxm.security.oauth2.bean.MockData;

import lombok.Data;

/**
 * <p>用作测试时的数据，正式应该是取数据库中数据</p>
 *
 * @author 王森明
 * @date 2021/4/25 14:15
 * @since 1.0.0
 */
 @Data
public class MyUser implements java.io.Serializable {
    private String username;

    private String password;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

}