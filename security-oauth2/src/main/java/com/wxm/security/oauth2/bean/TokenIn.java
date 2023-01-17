package com.wxm.security.oauth2.bean;

import lombok.*;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/4/26 11:28
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TokenIn {
    private String client_id;
    private String client_secret;
    private String username;
    private String password;
    private String grant_type="password";
    private String scope="reda";
}
