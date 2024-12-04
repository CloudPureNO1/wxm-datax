package com.wxm.druid.entity.biz.oauth2;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.sql.Blob;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * OAuth 2.0 刷新令牌
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("oauth_refresh_token")
public class OauthRefreshToken {

    @TableField("token_id")
    private String tokenId;

    @TableField("token")
    private Blob token;

    @TableField("`authentication`")
    private Blob authentication;


}
