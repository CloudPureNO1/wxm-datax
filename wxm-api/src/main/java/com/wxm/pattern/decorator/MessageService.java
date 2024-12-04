package com.wxm.pattern.decorator;

/**
 * 基础实现
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 11:59:20
 */
public class MessageService implements BaseApiService {
    @Override
    public String getMessage(String name) {
        // 实现获取消息的逻辑
        return "Hello, " + name;
    }
}
