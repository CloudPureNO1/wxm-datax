package com.wxm.base.constrant;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/4/1 9:51
 * @since 1.0.0
 */
public class SysConstrant {
    /**
     * 用户状态  0-无效
     */
    public static final String USER_STATUS_INVALID="0";
    /**
     * 用户状态  1-正常
     */
    public static final String USER_STATUS_NORMAL="1";
    /**
     * 用户状态  2-注销
     */
    public static final String USER_STATUS_WRITE_OFF="2";
    /**
     * 用户状态  3-锁定
     */
    public static final String USER_STATUS_LOCK="3";


    /**
     * 定义特殊字符，枚举类型中code与msg之间的分割符号  ->
     */
    public static final String SPECIAL_CHARACTER_CODE_MSG="->";

    /**
     * 详细异常信息前的分割符号 <=>
     *     主要用于分割数据库详细异常信息
     */
    public static final String SPECIAL_CHARACTER_BEFORE_DETAIL_MSG="<=>";


    /**
     * 用户信息校验失败
     */
    public static final String USER_INFO_CHECK_FAILURE_MSG="用户信息校验失败";
}
