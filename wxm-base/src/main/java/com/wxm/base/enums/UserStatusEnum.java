package com.wxm.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-06-17 16:47:01
 */
@Getter
@AllArgsConstructor
public enum UserStatusEnum {
    INVALID("0","无效"),
    NORMAL("1","正常"),
    LOGGED_OUT("2","注销"),
    LOCKED("3","锁定") ;

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



    public  String getMessage(String msg){
        return "【"+message+"】"+msg;
    }
}
