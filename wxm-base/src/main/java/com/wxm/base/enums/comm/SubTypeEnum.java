package com.wxm.base.enums.comm;

import java.util.Arrays;

/**
 * <p>RBAC权限管理系统接口信息</p>
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2023-05-11 14:34:57
 */
public enum SubTypeEnum{
    SUB_TYPE_RBAC("rbac","权限系统管理 【rbac】"),
    SUB_TYPE_QUARTZ("quartz","定时任务 【quartz】"),
    SUB_TYPE_MALL("mall","商城【mall】"),


    Rbac_99999("99999","未定义的类型 【99999】") ;


    SubTypeEnum(String code, String message){
        this.code=code;
        this.message=message;
    }

    public static String getMessage(String code){
        for(SubTypeEnum apiInfoEnum: SubTypeEnum.values()){
            if(apiInfoEnum.getCode().equals(code)){
                return apiInfoEnum.getMessage();
            }
        }
        return "";
    }
    public static SubTypeEnum getInstance(String code){
        for(SubTypeEnum apiInfoEnum: SubTypeEnum.values()){
            if(code==apiInfoEnum.getCode()){
                return apiInfoEnum;
            }
        }
        return null;
    }


    public static SubTypeEnum getInstanceByMessage(String message){
        for(SubTypeEnum apiInfoEnum: SubTypeEnum.values()){
            if(message.equals(apiInfoEnum.getMessage())){
                return apiInfoEnum;
            }
        }
        return null;
    }

    public static String [] getSubTypes(){
        return Arrays.stream(SubTypeEnum.values()).map(SubTypeEnum::getCode).toArray(String[]::new);
    }
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
