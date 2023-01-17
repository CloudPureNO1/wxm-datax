package com.wxm.security.handler;

import com.alibaba.fastjson.JSON;
import com.wxm.base.dto.DataRtn;
import com.wxm.security.encoder.SM3PasswordEncoder;
import com.wxm.security.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.RemoteException;


/**
 * <p>登录成功处理逻辑</p>
 * <p>
 *     首先需要明白一件事，对于登入登出我们都不需要自己编写controller接口，Spring Security为我们封装好了。
 *     默认登入路径:/login，登出路径:/logout。当然我们可以也修改默认的名字。登录成功失败和登出的后续处理逻辑如何编写会在后面慢慢解释。
 *  当登录成功或登录失败都需要返回统一的json返回体给前台，前台才能知道对应的做什么处理。
 * 而实现登录成功和失败的异常处理需要分别实现AuthenticationSuccessHandler和AuthenticationFailureHandler接口并在WebSecurityConfig中注入，
 * 然后在configure(HttpSecurity http)方法中然后声明
 *
 * </p>
 *
 * @author 王森明
 * @date 2021/4/1 13:33
 * @since 1.0.0
 */
@Slf4j
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
       log.info(">>>>>>>登录成功开始处理");
        try{
           SecurityContext securityContext = SecurityContextHolder.getContext();
           UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
           // 此时的密码为空了


           //此处还可以进行一些处理，比如登录成功之后可能需要返回给前台当前用户有哪些菜单权限，
           //进而前台动态的控制菜单的显示等，具体根据自己的业务需求进行扩展
           DataRtn dataRtn= TokenUtil.getDataRtn(userDetail,redisTemplate,true);
           httpServletResponse.setCharacterEncoding("UTF-8");
           httpServletResponse.setContentType("text/json;charset=utf-8");
           httpServletResponse.getWriter().write(JSON.toJSONString(dataRtn));
       }catch (Exception e){
           log.error(">>>MyAuthenticationSuccessHandler 发生异常：{}",e.getMessage(),e);
           httpServletResponse.setCharacterEncoding("UTF-8");
           httpServletResponse.setContentType("text/json;charset=utf-8");
           httpServletResponse.getWriter().write(JSON.toJSONString(new DataRtn<>().error().message("MyAuthenticationSuccessHandler 发生异常").detailMsg(e.getMessage())));
       }

    }
}
