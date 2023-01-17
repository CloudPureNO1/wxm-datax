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
public enum BizSvcEnum {
    BIZ_SVC_1001(1001,"BIZ-SVC-1001","查询失败"),
    BIZ_SVC_1002(1002,"BIZ-SVC-1002","新增失败"),
    BIZ_SVC_1002_A(10021,"BIZ-SVC-1002A","新增失败"),
    BIZ_SVC_1003(1003,"BIZ-SVC-1003","修改失败"),
    BIZ_SVC_1003_A(10031,"BIZ-SVC-1003A","作废失败"),
    BIZ_SVC_1003_B(10032,"BIZ-SVC-1003B","重启失败"),
    BIZ_SVC_1004(1004,"BIZ-SVC-1004","删除失败"),
    BIZ_SVC_1004_A(10041,"BIZ-SVC-1004A","删除失败"),
    BIZ_SVC_1004_B(10042,"BIZ-SVC-1004B","批量删除失败"),
    // 新增、编辑
    BIZ_SVC_1005(1005,"BIZ-SVC-1005","保存"),
    BIZ_SVC_1006(1006,"BIZ-SVC-1006","失败"),
    BIZ_SVC_9999(9999,"BIZ-SVC-9999","其他异常")
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
       return Arrays.stream(BizSvcEnum.values()).filter(en->en.toString().equals(name)).findFirst().orElse(BIZ_SVC_9999).getMessage();
    }


    public String getMessage(String msg) {
        return msg==null?"":msg.trim()+message;
    }
}
