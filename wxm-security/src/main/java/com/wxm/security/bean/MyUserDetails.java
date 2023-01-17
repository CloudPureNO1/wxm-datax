package com.wxm.security.bean;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/4/27 17:56
 * @since 1.0.0
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MyUserDetails implements UserDetails, Serializable {

    private  String username;
    private  String password;
    private  boolean enabled;
    private  boolean accountNonExpired;
    private  boolean credentialsNonExpired;
    private  boolean accountNonLocked;
    private Collection<GrantedAuthority> authorities;

    public MyUserDetails setUsername(String username) {
        this.username = username;
        return this;
    }

    public MyUserDetails setPassword(String password) {
        this.password = password;
        return this;
    }

    public MyUserDetails setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public MyUserDetails setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    public MyUserDetails setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public MyUserDetails setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public MyUserDetails setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }
}
