package com.wxm.base.enums.comm;

/**
 * <p>RBAC权限管理系统接口信息</p>
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2023-05-11 14:34:57
 */
public enum RbacEnum implements ICommEnum {
    Rbac_11001("11001","用户列表查询 【11001】"),
    Rbac_11002("11002","用户列表查询 【11002】"),
    Rbac_11003("11003","用户列表查询 【11003】"),
    Rbac_12001("12001","用户新增 【12001】"),
    Rbac_13001("13001","用户修改 【13001】"),
    Rbac_13002("13002","修改 普通定时任务 【13002】"),
    Rbac_14001("14001","用户删除【14001】"),
    Rbac_14002("14002","用户删除(批量) 【14002】"),


    Rbac_21001("21001","用户组列表查询 【21001】"),
    Rbac_21002("21002","用户组列表查询(分页查询） 【21002】"),
    Rbac_22001("22001","新增用户组 【22001】"),
    Rbac_22002("22002","给用户组添加用户 【22002】"),
    Rbac_22003("22003","给用户组添加角色 【22003】"),

    Rbac_23001("23001","编辑用户组 【23001】"),

    Rbac_24001("24001","删除用户组 【24001】"),
    Rbac_24002("24002","移除用户组中的用户 【24002】"),
    Rbac_24003("24003","移除用户组中的角色 【24003】"),
    Rbac_24004("24004","用户删除（批量） 【24004】"),

    Rbac_31001("31001","角色列表查询 【31001】"),
    Rbac_31002("31002","角色列表查询(分页) 【31002】"),
    Rbac_31003("31003","角色列表查询(分页) 【31003】"),
    Rbac_31004("31004","获取登录用户的角色 【31004】"),


    Rbac_32001("32001","角色新增 【32001】"),
    Rbac_32002("32002","编辑/新增 角色的资源 【32002】"),
    Rbac_32003("32003","给角色添加权限 【32003】"),
    Rbac_32004("32004","给角色添加接口 【32004】"),
    Rbac_32005("32005","编辑/新增 角色的权限 【32005】"),
    Rbac_32006("32006","编辑/新增 角色的接口 【32006】"),

    Rbac_33001("33001","角色修改 【33001】"),

    Rbac_34001("34001","角色删除 【34001】"),
    Rbac_34002("34002","移除角色中的权限 【34002】"),
    Rbac_34003("34003","移除角色中的接口 【34003】"),
    Rbac_34004("34004","删除角色（批量) 【34004】"),

    Rbac_41001("41001","资源列表查询 【41001】"),
    Rbac_41002("41002","资源列表查询(分页) 【41002】"),
    Rbac_41003("41003","资源列表查询(树形结构) 【41003】"),
    Rbac_41004("41004","资源查询（根据Id) 【41004】"),
    Rbac_41005("41005","资源列表查询(树形结构) 【41005】"),
    Rbac_41006("41006","资源列表查询(树形结构)（根据用户名） 【41006】"),

    Rbac_42001("42001","资源新增 【42001】"),
    Rbac_43001("43001","资源修改 【43001】"),
    Rbac_44001("44001","资源删除 【44001】"),

    Rbac_51001("51001","权限列表查询 【51001】"),
    Rbac_51002("51002","权限列表查询(分页) 【51002】"),
    Rbac_51003("51003","权限列表查询(分页),条件 【51003】"),
    Rbac_51004("51004","获取当前登录用户的权限列表 【51004】"),
    Rbac_51005("51005","获取所有权限和已经授权的权限 【51005】"),


    Rbac_52001("52001","权限新增 【52001】"),
    Rbac_53001("53001","权限修改 【53001】"),
    Rbac_54001("54001","权限删除 【54001】"),
    Rbac_54002("54002","权限删除(批量) 【54002】"),

    Rbac_61001("61001","字典列表查询 【61001】"),
    Rbac_61002("61002","字典列表查询 【61002】"),
    Rbac_61003("61003","字典列表查询 分页条件查询 【61003】"),

    Rbac_62001("62001","字典新增 【62001】"),
    Rbac_63001("63001","字典编辑 【63001】"),
    Rbac_64001("64001","字典删除 【64001】"),
    Rbac_64002("64002","字典批量删除 【64002】"),


    Rbac_71002("71002","接口列表查询（分页，条件） 【71002】"),
    Rbac_71003("71003","接口列表查询（分页，条件） 【71003】"),
    Rbac_71005("71005","获取所有接口和已经授权的接口 【71005】"),


    Rbac_72001("72001","接口新增 【72001】"),
    Rbac_73001("73001","接口修改 【73001】"),
    Rbac_74001("74001","接口删除 【74001】"),
    Rbac_74002("74002","接口删除(批量)【74002】"),

    Rbac_99999("99999","未定义的接口 【99999】") ;


    RbacEnum(String code, String message){
        this.code=code;
        this.message=message;
    }

    public static String getMessage(String code){
        for(RbacEnum apiInfoEnum: RbacEnum.values()){
            if(apiInfoEnum.getCode().equals(code)){
                return apiInfoEnum.getMessage();
            }
        }
        return "";
    }
    public static RbacEnum getInstance(String code){
        for(RbacEnum apiInfoEnum: RbacEnum.values()){
            if(code.equals(apiInfoEnum.getCode())){
                return apiInfoEnum;
            }
        }
        return null;
    }


    public static RbacEnum getInstanceByMessage(String message){
        for(RbacEnum apiInfoEnum: RbacEnum.values()){
            if(message.equals(apiInfoEnum.getMessage())){
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
