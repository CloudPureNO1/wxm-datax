package com.wxm.base.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>数据库层service抛出异常</p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/4/29 14:00
 * @since 1.0.0
 */
@Getter
@Setter
public class DbSvcException extends BaseException {
    private String code;
    private String message;
    private String detailMsg;
    public DbSvcException() {
        super();
    }
    public DbSvcException(String message){
        super();
        this.code=message;
    }

    public DbSvcException(String code, String message) {
        super(message);
        this.code=code;
        this.message=message;
    }
    public DbSvcException(String code, String message,String detailMsg) {
        super(message);
        this.code=code;
        this.message=message;
        this.detailMsg = detailMsg;
    }
    public DbSvcException(String code, String message , Throwable cause) {
        super(message,cause);
        this.code=code;
        this.message=message;
    }
    public DbSvcException(String code, String message ,String detailMsg, Throwable cause) {
        super(message,cause);
        this.code=code;
        this.message=message;
        this.detailMsg = detailMsg;
    }
    public DbSvcException(String message, Throwable cause) {
        super(message, cause);
    }
}
