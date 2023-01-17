package com.wxm.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/4/29 14:11
 * @since 1.0.0
 */
@AllArgsConstructor
@Getter
public enum ApiEnum {
    API_1001(1001,"Api-1001","请求地址有误"),
    API_1002(1002,"API-1002","参数异常"),
    // ClassNotFoundException
    API_1003(1003,"API-1003","反射异常:没有找到类"),
    // NoSuchMethodException
    API_1004(1004,"API-1004","反射异常:没有对应方法"),
    // IllegalAccessException
    API_1005(1005,"API-1005","反射异常:非法访问"),
    // InvocationTargetException
    API_1006(1006,"API-1006","反射异常:调用异常"),

    API_9999(9999,"API-9999","其他异常")
    ;

    private int ordinal;
    private String name;
    private String message;


    /**
     * Returns the name of this enum constant, as contained in the
     * declaration.  This method may be overridden, though it typically
     * isn't necessary or desirable.  An enum type should override this
     * method when a more "programmer-friendly" string form exists.
     *
     * @return the name of this enum constant
     */
    @Override
    public String toString() {
        return name;
    }


    public static String msg(String name,Class t) {
       return Arrays.stream(ApiEnum.values()).filter(en->en.toString().equals(name)).findFirst().orElse(API_9999).getMessage();
    }

    public  String getMessage(String msg){
        return "【"+message+"】"+msg;
    }
}
