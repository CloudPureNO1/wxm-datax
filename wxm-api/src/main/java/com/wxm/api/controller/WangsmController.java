package com.wxm.api.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-06-20 10:11:44
 */
@Slf4j
@RequestMapping("/wangsm")
@RestController
public class WangsmController {


    @GetMapping("/invalid")
    public String invalid(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            log.error("Invalidating session: " + session.getId());
            session.invalidate();
        }

        // 清理 Security 上下文，其中包含登录认证信息

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(null);

        SecurityContextHolder.clearContext();

        return "设置session无效，成功";
    }
}
