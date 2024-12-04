package com.wxm.druid.entity.biz.oauth2;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.sql.Blob;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * OAuth 2.0 访问令牌
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("oauth_access_token")
public class OauthAccessToken {

    @TableField("token_id")
    private String tokenId;

    @TableField("token")
    private Blob token;

    @TableId(value = "authentication_id", type = IdType.AUTO)
    private String authenticationId;

    @TableField("user_name")
    private String userName;

    @TableField("client_id")
    private String clientId;

    @TableField("`authentication`")
    private Blob authentication;

    @TableField("refresh_token")
    private String refreshToken;


}
