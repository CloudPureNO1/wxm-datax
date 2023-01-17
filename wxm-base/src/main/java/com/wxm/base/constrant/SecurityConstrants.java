package com.wxm.base.constrant;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/6/15 10:02
 * @since 1.0.0
 */
public class SecurityConstrants {
    /**
     * 账号不可用
     * DisabledException  OAuth2 message
     */
    public static String oauth2_DisabledException_msg="User is disabled";
    /**
     * 账号被锁住
     * LockedException  OAuth2  message
     */
    public static String oauth2_LockedException_msg="User account is locked";
    /**
     * 账号过期
     * AccountExpiredException  OAuth2 message
     */
    public static String oauth2_AccountExpiredException_msg="User account has expired";
    /**
     * 账号密码错误
     * BadCredentialsException  OAuth2 message
     */
    public static String oauth2_BadCredentialsException_msg="Bad credentials";
    /**
     * 账号密码过期
     * CredentialsExpiredException  OAuth2 message
     */
    public static String oauth2_CredentialsExpiredException_msg="User credentials have expired";

}
