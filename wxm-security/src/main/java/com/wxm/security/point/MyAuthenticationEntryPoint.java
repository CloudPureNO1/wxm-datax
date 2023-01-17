package com.wxm.security.point;

import com.alibaba.fastjson.JSON;
import com.wxm.base.dto.DataRtn;
import com.wxm.base.enums.SecurityInfoEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>匿名用户访问无权限资源时的异常</p>
 * <p>AuthenticationEntryPoint 该类用来统一处理 AuthenticationException 异常</p>
 * <p>
 *  而在前后端分离的情况下（比如前台使用VUE或JQ等）我们需要的是在前台接收到"用户未登录"的提示信息，
 *  所以我们接下来要做的就是屏蔽重定向的登录页面，并返回统一的json格式的返回体。
 *  而实现这一功能的核心就是实现AuthenticationEntryPoint并在WebSecurityConfig中注入，
 *  然后在configure(HttpSecurity http)方法中。AuthenticationEntryPoint主要是用来处理匿名用户访问无权限资源时的异常（
 *  即未登录，或者登录状态过期失效）
 * </p>
 *
 * @author 王森明
 * @date 2021/4/1 13:15
 * @since 1.0.0
 */
@Slf4j
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
       log.error(">>>>>>>登录、token等校验结果处理：{}",e.getMessage());
        DataRtn dataRtn=new DataRtn(SecurityInfoEnum.USER_LOGON_STATUS_INVALID.getCode(),SecurityInfoEnum.USER_LOGON_STATUS_INVALID.getMsg()).detailMsg(e.getMessage());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(dataRtn));
    }
}
