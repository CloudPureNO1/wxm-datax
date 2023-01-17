package com.wxm.security.handler;

import com.alibaba.fastjson.JSON;
import com.wxm.base.constrant.SysConstrant;
import com.wxm.base.dto.DataRtn;
import com.wxm.base.enums.SecurityInfoEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>登录失败处理逻辑</p>
 * <p>
 *
 *     登录失败处理器主要用来对登录失败的场景（密码错误、账号锁定等…）做统一处理并返回给前台统一的json返回体。
 *     还记得我们创建用户表的时候创建了账号过期、密码过期、账号锁定之类的字段吗，这里就可以派上用场了.
 * </p>
 *
 * @author 王森明
 * @date 2021/4/1 14:12
 * @since 1.0.0
 */
@Slf4j
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/json;charset=utf-8");
        DataRtn dataRtn=null;
        if(StringUtils.hasLength(e.getMessage())){
            String [] errorMsgs=e.getMessage().split(SysConstrant.SPECIAL_CHARACTER_CODE_MSG);
            if(SecurityInfoEnum.isExists(errorMsgs[0])){
                dataRtn=new DataRtn<>(errorMsgs[0],errorMsgs[1].split(SysConstrant.SPECIAL_CHARACTER_BEFORE_DETAIL_MSG)[0]);
                httpServletResponse.getWriter().write(JSON.toJSONString(dataRtn));
                return;
            }
        }


        if (e instanceof AccountExpiredException) {
            //账号过期
            dataRtn=new DataRtn<>(SecurityInfoEnum.USER_ACCOUNT_EXPIRED.getCode(),SecurityInfoEnum.USER_ACCOUNT_EXPIRED.getMsg());
        } else if (e instanceof BadCredentialsException) {
            //密码错误
            dataRtn=new DataRtn<>(SecurityInfoEnum.USER_CREDENTIALS_ERROR.getCode(),SecurityInfoEnum.USER_CREDENTIALS_ERROR.getMsg());
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
            dataRtn=new DataRtn<>(SecurityInfoEnum.USER_CREDENTIALS_EXPIRED.getCode(),SecurityInfoEnum.USER_CREDENTIALS_EXPIRED.getMsg());
        } else if (e instanceof DisabledException) {
            //账号不可用
            dataRtn=new DataRtn<>(SecurityInfoEnum.USER_ACCOUNT_DISABLE.getCode(),SecurityInfoEnum.USER_ACCOUNT_DISABLE.getMsg());
        } else if (e instanceof LockedException) {
            //账号锁定
            dataRtn=new DataRtn<>(SecurityInfoEnum.USER_ACCOUNT_LOCKED.getCode(),SecurityInfoEnum.USER_ACCOUNT_LOCKED.getMsg());
        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户不存在
            dataRtn=new DataRtn<>(SecurityInfoEnum.USER_ACCOUNT_NOT_EXIST.getCode(),SecurityInfoEnum.USER_ACCOUNT_NOT_EXIST.getMsg());
        }else{
            //其他错误
            dataRtn=new DataRtn<>(SecurityInfoEnum.UN_DEFINED_EXC.getCode(),SecurityInfoEnum.UN_DEFINED_EXC.getMsg());
        }
        log.error("【[]】{}:{}",dataRtn.getCode(),dataRtn.getMessage(),dataRtn.getDetailMsg());
        httpServletResponse.getWriter().write(JSON.toJSONString(dataRtn));
    }
}
