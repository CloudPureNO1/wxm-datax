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
public enum DecodeEnum {
    DECODE_1001(1001,"DECODE-1001","文件已经存在"),
    DECODE_1002(1002,"DECODE-1002","目录创建失败"),
    DECODE_1003(1003,"DECODE-1003","文件创建失败"),
    DECODE_1004(1004,"DECODE-1004","对象拷贝失败"),
    DECODE_1005(1005,"DECODE-1005","列表对象拷贝失败"),
    // UnsupportedEncodingException
    DECODE_1006(1006,"DECODE-1006","未支持的字符集编码"),
    DECODE_1007(1007,"DECODE-1007","Base64解码失败"),

    DECODE_9999(9999,"DECODE9999","其他异常")
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
       return Arrays.stream(DecodeEnum.values()).filter(en->en.toString().equals(name)).findFirst().orElse(DECODE_9999).getMessage();
    }

    public  String getMessage(String msg){
        return "【"+message+"】"+msg;
    }
}
