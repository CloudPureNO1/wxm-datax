package com.wxm.base.enums.comm;

/**
 * <p>商城接口信息</p>
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2023-05-11 14:34:57
 */
public enum MallEnum implements ICommEnum {
    Quartz_11001("11001", "查询定时任务和触发器基本信息 【11001】"),

    Quartz_99999("99999", "未定义的接口 【99999】");

    MallEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(String code){
        for(MallEnum apiInfoEnum: MallEnum.values()){
            if(apiInfoEnum.getCode().equals(code)){
                return apiInfoEnum.getMessage();
            }
        }
        return "";
    }
    public static MallEnum getInstance(String code) {
        for (MallEnum apiInfoEnum : MallEnum.values()) {
            if (code .equals(apiInfoEnum.getCode())) {
                return apiInfoEnum;
            }
        }
        return null;
    }


    public static MallEnum getInstanceByMessage(String message) {
        for (MallEnum apiInfoEnum : MallEnum.values()) {
            if (message.equals(apiInfoEnum.getMessage())) {
                return apiInfoEnum;
            }
        }
        return null;
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

    @Override
    public String msg() {
        return message;
    }
}
