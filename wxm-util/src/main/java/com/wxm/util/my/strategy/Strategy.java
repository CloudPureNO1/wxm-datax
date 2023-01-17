package com.wxm.util.my.strategy;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/10 13:19
 * @since 1.0.0
 */
public interface Strategy <T>{
    /**
     * 字符串转其他类型
     * @param value
     * @return
     */
    T parseToObject(String value);

    /**
     * 其他类型转字符串
     * @param value
     * @return
     */
    String transToString(T value);
}
