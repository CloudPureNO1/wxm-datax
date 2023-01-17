package com.wxm.security.oauth2.point;

import com.alibaba.fastjson.JSON;
import com.wxm.base.dto.DataRtn;
import com.wxm.base.enums.SecurityInfoEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>无效Token返回处理器</p>
 * <p>配置在资源服务器里</p>
 *
 * @author 王森明
 * @date 2021/6/11 17:38
 * @since 1.0.0
 */
@Component
public class MyTokenExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        int i = SecurityInfoEnum.TOKEN_EMPTY_ERROR.getName().indexOf(":");
        String msg = SecurityInfoEnum.TOKEN_EMPTY_ERROR.getName().substring(i + 1);
        if (msg.equals(authException.getMessage())) {
            msg = SecurityInfoEnum.TOKEN_EMPTY_ERROR.getMsg();
        } else {
            i = SecurityInfoEnum.TOKEN_ERROR.getName().indexOf(":");
            msg = SecurityInfoEnum.TOKEN_ERROR.getName().substring(i + 1);
            i = authException.getMessage().indexOf(":");
            if (i > 0 && msg.equals(authException.getMessage().substring(0, i))) {
                msg = SecurityInfoEnum.TOKEN_ERROR.getMsg();
            } else {
                msg = authException.getMessage();
            }
        }
        DataRtn dataRtn = new DataRtn().setCode(SecurityInfoEnum.TOKEN_ERROR.getCode()).message(msg).detailMsg(authException.getMessage());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(JSON.toJSONString(dataRtn));
    }
}
