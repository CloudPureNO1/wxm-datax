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
public enum OSSEnum {
    OSS_1001(1001, "OSS-UPLOAD-EXCEPTION", "OSS上传异常"),
    OSS_1002(1002, "OSS-DOWNLOAD-EXCEPTION", "OSS下载异常"),
    OSS_9999(9999, "OSS-9999", "其他异常");

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


    public static String msg(String name, Class t) {
        return Arrays.stream(OSSEnum.values()).filter(en -> en.toString().equals(name)).findFirst().orElse(OSS_9999).getMessage();
    }
}
