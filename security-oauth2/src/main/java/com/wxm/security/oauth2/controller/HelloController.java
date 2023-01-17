package com.wxm.security.oauth2.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/4/28 17:25
 * @since 1.0.0
 */
@RestController
public class HelloController {



    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/admin/hello")
    public String helloAdmin() {
        return "Hello,Admin!";
    }

    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/user/hello")
    public String helloUser() {
        return "Hello,User!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }
}
