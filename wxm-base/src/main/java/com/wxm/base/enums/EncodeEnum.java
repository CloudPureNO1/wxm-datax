package com.wxm.ENCODE.enums;

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
public enum EncodeEnum {
    ENCODE_1001(1001,"ENCODE-1001","文件已经存在"),
    ENCODE_1002(1002,"ENCODE-1002","目录创建失败"),
    ENCODE_1003(1003,"ENCODE-1003","文件创建失败"),
    ENCODE_1004(1004,"ENCODE-1004","对象拷贝失败"),
    ENCODE_1005(1005,"ENCODE-1005","列表对象拷贝失败"),
    // UnsupportedEncodingException
    ENCODE_1006(1006,"ENCODE-1006","未支持的字符集编码"),
    ENCODE_1007(1007,"ENCODE-1007","Base64编码失败"),

    ENCODE_9999(9999,"ENCODE9999","其他异常")
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
       return Arrays.stream(EncodeEnum.values()).filter(en->en.toString().equals(name)).findFirst().orElse(ENCODE_9999).getMessage();
    }

    public  String getMessage(String msg){
        return "【"+message+"】"+msg;
    }
}
