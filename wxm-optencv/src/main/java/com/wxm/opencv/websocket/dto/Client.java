package com.wxm.opencv.websocket.dto;

import lombok.*;
import lombok.experimental.Accessors;

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
@EqualsAndHashCode
@ToString
public class Client implements java.io.Serializable {
    /**
     * 用户登录
     */
//    private String username;

    /**
     * token 登录的使用 token
     */
//    private String token;

    /**
     * 每次链接
     * 1. token 登录的使用 token
     * 2. 用户登录 :username
     */
    private String uid;

    /**
     * Websocket session
     */
    private javax.websocket.Session session;
}
