package com.wxm.base.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/23 13:32
 * @since 1.0.0
 */
@Getter
@Setter
public class EncodeException extends BaseException{
    private String code;
    private String message;
    private String detailMsg;
    public EncodeException() {
        super();
    }
    public EncodeException(String message){
        super();
        this.message=message;
    }

    public EncodeException(String code, String message) {
        super(message);
        this.code=code;
        this.message=message;
    }
    public EncodeException(String code, String message,String detailMsg) {
        super(message);
        this.code=code;
        this.message=message;
        this.detailMsg = detailMsg;
    }
    public EncodeException(String code, String message , Throwable cause) {
        super(message,cause);
        this.code=code;
        this.message=message;
    }
    public EncodeException(String code, String message ,String detailMsg, Throwable cause) {
        super(message,cause);
        this.code=code;
        this.message=message;
        this.detailMsg = detailMsg;
    }
    public EncodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
