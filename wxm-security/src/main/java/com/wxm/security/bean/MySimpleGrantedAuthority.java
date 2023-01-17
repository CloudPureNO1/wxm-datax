package com.wxm.security.bean;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

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
public class MySimpleGrantedAuthority implements GrantedAuthority, Serializable {
    private  String role;
    private String authority;


    public MySimpleGrantedAuthority(String roleCode) {
        this.role = roleCode;

    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
