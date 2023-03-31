package com.wxm.opencv.websocket.dto;

import lombok.*;
import lombok.experimental.Accessors;

import javax.websocket.Session;
import java.util.Objects;

/**
 * Websocket Client
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-03-10 16:49:29
 */
@Accessors(chain = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Client implements java.io.Serializable {

    /**
     * 每次链接时的socket 唯一标识
     * 1. token 登录的使用 token
     * 2. 用户登录 :username
     */
    private String uid;


    /**
     * Websocket session
     */
    private javax.websocket.Session session;


}
