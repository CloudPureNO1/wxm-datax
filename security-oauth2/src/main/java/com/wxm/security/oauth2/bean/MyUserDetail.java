package com.wxm.security.oauth2.bean;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/4/27 17:56
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MyUserDetail implements UserDetails, Serializable {

    private  String username;
    private String password;
    private  boolean enabled;
    private  boolean accountNonExpired;
    private  boolean credentialsNonExpired;
    private  boolean accountNonLocked;
    private  List<MySimpleGrantedAuthority> authorities;

}
