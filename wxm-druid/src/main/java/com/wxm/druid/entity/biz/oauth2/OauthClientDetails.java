package com.wxm.druid.entity.biz.oauth2;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangsm
 * @since 2021-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("oauth_client_details")
@ApiModel(value="OauthClientDetailsModel对象", description="")
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键，客户端ID")
    @TableId(value = "client_id", type = IdType.AUTO)
    private String clientId;

    @ApiModelProperty(value = "客户端所能访问的资源id集合，多个资源时用逗号(,)分隔")
    @TableField("resource_ids")
    private String resourceIds;

    @ApiModelProperty(value = "客户端访问密匙")
    @TableField("client_secret")
    private String clientSecret;

    @ApiModelProperty(value = "客户端申请的权限范围，可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔")
    @TableField("scope")
    private String scope;

    @ApiModelProperty(value = "客户端支持的授权许可类型(grant_type)，可选值包括authorization_code,password,refresh_token,implicit,client_credentials,若支持多个授权许可类型用逗号(,)分隔")
    @TableField("authorized_grant_types")
    private String authorizedGrantTypes;

    @ApiModelProperty(value = "客户端重定向URI，当grant_type为authorization_code或implicit时, 在Oauth的流程中会使用并检查与数据库内的redirect_uri是否一致")
    @TableField("web_server_redirect_uri")
    private String webServerRedirectUri;

    @ApiModelProperty(value = "客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔")
    @TableField("authorities")
    private String authorities;

    @ApiModelProperty(value = "设定客户端的access_token的有效时间值(单位:秒)，若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时)")
    @TableField("access_token_validity")
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "设定客户端的refresh_token的有效时间值(单位:秒)，若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天)")
    @TableField("refresh_token_validity")
    private Integer refreshTokenValidity;

    @ApiModelProperty(value = "这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据")
    @TableField("additional_information")
    private String additionalInformation;

    @ApiModelProperty(value = "设置用户是否自动批准授予权限操作, 默认值为 ‘false’, 可选值包括 ‘true’,‘false’, ‘read’,‘write’.")
    @TableField("autoapprove")
    private String autoapprove;

    @ApiModelProperty(value = "记录创建时间")
    @TableField("create_time")
    private Date createTime;


}
