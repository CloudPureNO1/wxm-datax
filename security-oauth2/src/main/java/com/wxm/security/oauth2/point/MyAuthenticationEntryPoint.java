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
import java.util.Date;

/**
 * <p>客户端信息异常</p>
 * <p>配置在授权服务配置中</p>
 * </p>
 *
 * @author 王森明
 * @date 2021/4/1 13:15
 * @since 1.0.0
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //DataRtn dataRtn=new DataRtn(SecurityInfoEnum.CLIENT_ID_OR_CLIENT_SECRET_ERROR.getCode(),SecurityInfoEnum.CLIENT_ID_OR_CLIENT_SECRET_ERROR.getMsg());
        DataRtn dataRtn = new DataRtn().setCode(SecurityInfoEnum.CLIENT_ID_OR_CLIENT_SECRET_ERROR.getCode())
                .setMessage(SecurityInfoEnum.CLIENT_ID_OR_CLIENT_SECRET_ERROR.getMsg())
                .setDetailMsg(SecurityInfoEnum.CLIENT_ID_OR_CLIENT_SECRET_ERROR.toString()+":"+e.getMessage())
                .setRsTime(System.currentTimeMillis());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        httpServletResponse.getWriter().write(JSON.toJSONString(dataRtn));
    }
}
