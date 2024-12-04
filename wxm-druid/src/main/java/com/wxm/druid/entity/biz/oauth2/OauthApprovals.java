package com.wxm.druid.entity.biz.oauth2;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("oauth_approvals")
public class OauthApprovals {

    @TableField("userId")
    private String userId;

    @TableField("clientId")
    private String clientId;

    @TableField("scope")
    private String scope;

    @TableField("`status`")
    private String status;

    @TableField("expiresAt")
    private LocalDateTime expiresAt;

    @TableField("lastModifiedAt")
    private LocalDateTime lastModifiedAt;


}
