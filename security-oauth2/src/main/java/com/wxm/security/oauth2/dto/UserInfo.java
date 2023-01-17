package com.wxm.security.oauth2.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/6/16 14:14
 * @since 1.0.0
 */
@Data
public class UserInfo implements java.io.Serializable{

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "备注")
    private String userDesc;

    @ApiModelProperty(value = "用户级别： 1 管理员 2超级管理员 3普通用户 -1 游客")
    private String userRate;

    @ApiModelProperty(value = "用户类型（角色类型）")
    private String userType;

}
