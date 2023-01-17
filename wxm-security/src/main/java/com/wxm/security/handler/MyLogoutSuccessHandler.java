package com.wxm.security.handler;

import com.alibaba.fastjson.JSON;
import com.wxm.base.dto.DataRtn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * <p>登出成功处理逻辑</p>
 * <p>同样的登出也要将登出成功时结果返回给前台，并且登出之后进行将cookie失效或删除</p>
 *
 * @author 王森明
 * @date 2021/4/1 14:26
 * @since 1.0.0
 */
@Slf4j
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // 让 session 失效　
/*        request.getSession(true)：若存在会话则返回该会话，否则新建一个会话。 等同于 HttpServletRequest.getSession()

        request.getSession(false)：若存在会话则返回该会话，否则返回NULL
        当向Session中存取登录信息时，一般建议：HttpSession session =request.getSession();

当从Session中获取登录信息时，一般建议：HttpSession session =request.getSession(false);
        */

        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            log.error("Invalidating session: " + session.getId());
            session.invalidate();
        }

        // 清理 Security 上下文，其中包含登录认证信息

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(null);

        SecurityContextHolder.clearContext();
        log.info("退出成功");
        DataRtn dataRtn = new DataRtn().success().message("退出成功");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(dataRtn));
    }
}
