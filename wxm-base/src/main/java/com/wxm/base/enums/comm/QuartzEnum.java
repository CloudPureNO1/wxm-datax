package com.wxm.base.enums.comm;

/**
 * <p>定时任务接口信息</p>
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2023-05-11 14:34:57
 */
public enum QuartzEnum implements ICommEnum {
    Quartz_11001("11001", "查询定时任务和触发器基本信息 【11001】"),
    Quartz_11002("11002", "查询触发器列表 【11002】"),
    Quartz_11003("11003", "查询触发器列表 【11003】"),
    Quartz_11004("11004", "根据定时任务名称查询datax的数据同步信息 【11004】"),
    Quartz_12001("12001", "新增quart+datax 数据同步的定时任务 【12001】"),
    Quartz_12002("12002", "新增普通定时任务 【12002】"),
    Quartz_13001("13001", "修改 quart+datax 数据同步的定时任务 【13001】"),
    Quartz_13002("13002", " 修改 普通定时任务 【13002】"),
    Quartz_14001("14001", "删除quart+datax 数据同步的定时任务 【14001】"),
    Quartz_14002("14002", " 删除普通定时任务 【14002】"),
    Quartz_15001("15001", "Quartz定时任务停止 【15001】"),
    Quartz_16001("16001", "Quartz定时任务恢复 【16001】"),
    Quartz_99999("99999", "未定义的接口 【99999】");

    QuartzEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(String code){
        for(QuartzEnum apiInfoEnum: QuartzEnum.values()){
            if(apiInfoEnum.getCode().equals(code)){
                return apiInfoEnum.getMessage();
            }
        }
        return "";
    }
    public static QuartzEnum getInstance(String code) {
        for (QuartzEnum apiInfoEnum : QuartzEnum.values()) {
            if (code .equals(apiInfoEnum.getCode())) {
                return apiInfoEnum;
            }
        }
        return null;
    }


    public static QuartzEnum getInstanceByMessage(String message) {
        for (QuartzEnum apiInfoEnum : QuartzEnum.values()) {
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
