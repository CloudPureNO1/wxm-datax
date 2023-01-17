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
public enum JobEnum {
    JOB_1001(1001,"JOB-1001","新增定时任务失败"),
    JOB_1002(1002,"JOB-1002","停止定时任务失败"),
    JOB_1003(1003,"JOB-1003","恢复（取消暂停） 定时任务 失败"),
    JOB_1004(1004,"JOB-1004","重新组建定时任务（rescheduleJOB）失败"),
    JOB_1005(1005,"JOB-1005","删除定时任务失败"),
    JOB_1006(1006,"JOB-1006","Datax 用户名或密码为空"),

    JOB_9999(9999,"JOB-9999","其他异常")
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
       return Arrays.stream(JobEnum.values()).filter(en->en.toString().equals(name)).findFirst().orElse(JOB_9999).getMessage();
    }
}
