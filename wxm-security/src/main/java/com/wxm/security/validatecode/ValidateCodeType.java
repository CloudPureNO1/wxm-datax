package com.wxm.security.validatecode;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-05-21 14:38:29
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ValidateCodeType {
    // 关闭验证码
    NONE("-1"),
    // 标准
    STANDARD("0"),
    // 滑动验证码
    SLID("1");
    private String type;

    public String type() {
        return type;
    }
}
