package com.wxm.core.move;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-25 16:03:17
 */
public enum MoveEnum {
    BEFORE("before"),
    AFTER("after"),
    INNER("inner"),
    NONE("none");

    public String getName() {
        return name;
    }


    private String name;

    MoveEnum(String name) {
        this.name = name;
    }
    // 静态方法获取name
    public static String getName(MoveEnum moveEnum) {
        return moveEnum.getName();
    }
}
