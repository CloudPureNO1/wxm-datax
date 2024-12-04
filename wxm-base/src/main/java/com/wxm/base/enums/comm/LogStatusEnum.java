package com.wxm.base.enums.comm;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-06-18 10:13:44
 */
@AllArgsConstructor
@Getter
public enum LogStatusEnum {
    REQUEST("1", "请求"),
    RESPONSE("2", "响应"),
    EXCEPTION("3", "异常");

    private String code;
    private String message;
}
