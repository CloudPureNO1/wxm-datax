package com.wxm.base.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/4/28 17:15
 * @since 1.0.0
 */
@Accessors(chain = true)
@Data
public class DataRtn<T>{
    private String code;
    private String message;
    private T data;
    private String detailMsg;
    private Long rsTime;
    public DataRtn() {
        this.rsTime=System.currentTimeMillis();
    }
    public DataRtn(String code,String message) {
        this.code = code;
        this.message = message;
        this.rsTime=System.currentTimeMillis();
    }



    public DataRtn success(){
        this.code="0";
        return this;
    }
    public DataRtn failure(){
        this.code="-1";
        return this;
    }
    public DataRtn error(){
        this.code="-2";
        return this;
    }
    public DataRtn code(String code){
        this.code=code;
        return this;
    }

    public DataRtn data(T data){
        this.data=data;
        return this;
    }
    public DataRtn message(String message){
        this.message=message;
        return this;
    }
    public DataRtn detailMsg(String detailMsg){
        this.detailMsg = detailMsg;
        return this;
    }


    public static DataRtn ok(){
        DataRtn dataRtn=new DataRtn().success();
        return dataRtn;
    }
    public static DataRtn ok(String message){
        DataRtn dataRtn=new DataRtn().success().setMessage(message);
        return dataRtn;
    }
    public static <T>DataRtn<T> ok(T t){
        DataRtn<T> dataRtn=new DataRtn<T>().success().setData(t);
        return dataRtn;
    }
    public static <T>DataRtn<T> ok(T t,String message){
        DataRtn<T> dataRtn=new DataRtn<T>().success().setData(t).setMessage(message);
        return dataRtn;
    }


    public static DataRtn error(String message){
         return new DataRtn("-2",message);
    }
    public static DataRtn error(String code,String message){
         return new DataRtn(code,message);
    }

}
