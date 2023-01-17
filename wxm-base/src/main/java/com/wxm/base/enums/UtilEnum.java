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
public enum UtilEnum {
    UTIL_1001(1001,"UTIL-1001","文件已经存在"),
    UTIL_1002(1002,"UTIL-1002","目录创建失败"),
    UTIL_1003(1003,"UTIL-1003","文件创建失败"),
    UTIL_1004(1004,"UTIL-1004","对象拷贝失败"),
    UTIL_1005(1005,"UTIL-1005","列表对象拷贝失败"),
    // UnsupportedEncodingException
    UTIL_1006(1006,"UTIL1006","未支持的字符集编码"),


    UTIL_9999(9999,"UTIL-9999","其他异常")
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
       return Arrays.stream(UtilEnum.values()).filter(en->en.toString().equals(name)).findFirst().orElse(UTIL_9999).getMessage();
    }

    public  String getMessage(String msg){
        return "【"+message+"】"+msg;
    }
}
