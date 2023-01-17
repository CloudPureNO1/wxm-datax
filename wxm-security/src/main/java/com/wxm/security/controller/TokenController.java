package com.wxm.security.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.wxm.base.dto.DataRtn;
import com.wxm.security.bean.Token;
import com.wxm.security.bean.TokenIn;
import com.wxm.security.encoder.SM3PasswordEncoder;
import com.wxm.security.service.ITestService;
import com.wxm.security.util.TokenUtil;
import com.wxm.util.my.MyUUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/27 10:26
 * @since 1.0.0
 */
@Slf4j
@RestController
public class TokenController {
    private final UserDetailsService userDetailsService;
    private final RedisTemplate redisTemplate;
    private final ITestService testService;

    public TokenController(UserDetailsService userDetailsService, RedisTemplate redisTemplate, ITestService testService) {
        this.userDetailsService = userDetailsService;
        this.redisTemplate = redisTemplate;
        this.testService = testService;
    }


    /**
     * // 不采用Get   springsecurity 默认 form Post 提交，在 MyAuthenticationSuccessHandler 中处理返回成功的token,
     * 此处时单独设置获取token的方法
     *
     * 需要构造出 org.springframework.security.core.userdetails.User 对象并返回
     * String username：用户名
     * String password： 密码
     * boolean enabled： 账号是否可用
     * boolean accountNonExpired：账号是否 非过期
     * boolean credentialsNonExpired：密码是否 非过期
     * boolean accountNonLocked：账号是否 非锁定
     * Collection<? extends GrantedAuthority> authorities)：用户权限列表
     *
     * @param jsonStr
     * @return
     */
    @PostMapping(value={"/auth/token"})
    public DataRtn getToken(@RequestBody String jsonStr)  {
        log.info("**********************************************");
        log.info("传入参数：{}", jsonStr);
        log.info("**********************************************");

        TokenIn tokenIn = JSON.parseObject(jsonStr, TokenIn.class);
        SM3PasswordEncoder passwordEncoder=new SM3PasswordEncoder();

        /**
         * userDetailsService.loadUserByUsername
         * 返回了 @see {org.springframework.security.core.userdetails.User}
         */
        UserDetails userDetails = userDetailsService.loadUserByUsername(tokenIn.getUsername());


        if (null == userDetails) {
            throw new InternalAuthenticationServiceException("账号不存在");
        }
        if (!userDetails.getPassword().equals(passwordEncoder.encode(tokenIn.getPassword()))){
            throw new BadCredentialsException("账号或密码错误");
        }
        if (!userDetails.isEnabled()) {
            throw new DisabledException("账号不可用");
        }
        if (!userDetails.isAccountNonLocked()){
            throw new LockedException("账号已经锁定");
        }
        if (!userDetails.isAccountNonExpired()){
            throw new AccountExpiredException("账号已经过期");
        }
        if (!userDetails.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("密码已经过期");
        }

        return TokenUtil.getDataRtn(userDetails,redisTemplate,true);
    }




    @PostMapping("/refresh/token")
    public DataRtn refreshToken(@RequestParam("username") String username,@RequestParam("password") String password){
        TokenIn tokenIn = new TokenIn(username,password);
        log.info("**********************************************");
        log.info("传入参数：{}", JSON.toJSONString(tokenIn));
        log.info("**********************************************");

        SM3PasswordEncoder passwordEncoder=new SM3PasswordEncoder();


        UserDetails user = userDetailsService.loadUserByUsername(tokenIn.getUsername());
        if (null == user) {
            throw new InternalAuthenticationServiceException("账号不存在");
        }
        if (!user.getPassword().equals(passwordEncoder.encode(tokenIn.getPassword()))){
            throw new BadCredentialsException("账号或密码错误");
        }
        if (!user.isEnabled()) {
            throw new DisabledException("账号不可用");
        }
        if (!user.isAccountNonLocked()){
            throw new LockedException("账号已经锁定");
        }
        if (!user.isAccountNonExpired()){
            throw new AccountExpiredException("账号已经过期");
        }
        if (!user.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("密码已经过期");
        }

        return TokenUtil.getDataRtn(user,redisTemplate,true);
    }






    @PostMapping("/user/index")
    public String getMsg() {
        return "欢迎";
    }

    @PreAuthorize("hasAuthority('MTMS_ROLE_ADMIN')")  //  不设置，表示所有登录验证过的用户都有权限
    @PostMapping("/admin/index")
    public String getAdminIndex() {
        return "欢迎，管理员";
    }

    @PostMapping("/test")
    public String test() {
        testService.test();
        return "欢迎";
    }


    /**
     * 没有登录用户访问的地址，跳转到系统默认的登录地址，采用自己输出信息，而不是弹出登录界面或登录框
     * MyAuthenticationEntryPoint  使用此类替代
     * @return
     */
/*    @GetMapping("/login")
    public String noLogin(){
        return JSON.toJSONString(new DataRet("1","没有登录"));
    }*/




}
