package com.wxm.security.handler;

import com.alibaba.fastjson.JSON;
import com.wxm.base.dto.DataRtn;
import com.wxm.base.enums.SecurityInfoEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>登录校验用户无访问权限异常处理</p>
 * <p>AccessDeniedHandler 该类用来统一处理 AccessDeniedException 异常</p>
 * <p>此处我们可以自定义403响应的内容,让他返回我们的错误逻辑提示</p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/4/2 14:58
 * @since 1.0.0
 */
@Slf4j
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        log.error("很抱歉，您没有该访问权限:{}",e.getMessage());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/json;charset=utf-8");
        DataRtn dataRtn=new DataRtn()
                .code(SecurityInfoEnum.ACCESS_DENIED_EXC.getCode())
                .message(SecurityInfoEnum.ACCESS_DENIED_EXC.getMsg())
                .detailMsg(SecurityInfoEnum.ACCESS_DENIED_EXC.getMsg()+":"+e.getMessage());
        httpServletResponse.getWriter().write(JSON.toJSONString(dataRtn));
    }
}