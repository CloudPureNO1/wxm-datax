package com.wxm.push.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * TODO
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-04-10 16:28:48
 */
@Accessors(chain = true)
@Data
public class MsgDto<T> implements java.io.Serializable{
    /**
     * 0 : 下线通知
     * 1 : 上线通知
     * 2 ： 消息
     */
    private String type;
    /**
     * 发送者uid
     */
    private String uid;
    private T data;
}
