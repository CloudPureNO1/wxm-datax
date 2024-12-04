package com.wxm.druid.entity.biz.oauth2;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.sql.Blob;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * OAuth 2.0 授权码
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("oauth_code")
public class OauthCode {

    @TableField("`code`")
    private String code;

    @TableField("`authentication`")
    private Blob authentication;


}
