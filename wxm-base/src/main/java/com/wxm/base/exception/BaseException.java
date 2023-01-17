package com.wxm.base.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>作为基础异常类，不直接使用</p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/4/29 14:00
 * @since 1.0.0
 */
 @Getter
 @Setter
public class BaseException extends Exception {
    private String code;
    private String message;
    private String detailMsg;
    public BaseException() {
        super();
    }
    public BaseException(String message){
        super();
        this.code=message;
    }

    public BaseException(String code, String message) {
        super(message);
        this.code=code;
        this.message=message;
    }
    public BaseException(String code,String message, String detailMsg) {
        super(message);
        this.code=code;
        this.message=message;
        this.detailMsg=detailMsg;
    }
    public BaseException(String code, String message , Throwable cause) {
        super(message,cause);
        this.code=code;
        this.message=message;
    }
    public BaseException(String code, String message ,String detailMsg, Throwable cause) {
        super(message,cause);
        this.code=code;
        this.message=message;
        this.detailMsg=detailMsg;
    }
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
