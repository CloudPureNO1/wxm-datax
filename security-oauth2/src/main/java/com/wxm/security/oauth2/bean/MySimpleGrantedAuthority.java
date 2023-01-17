package com.wxm.security.oauth2.bean;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/4/28 9:51
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MySimpleGrantedAuthority implements GrantedAuthority, Serializable {
    private  String role;
    private  String authority;

    public MySimpleGrantedAuthority(String role) {
        this.role=role;
        this.authority=role;
    }
}
