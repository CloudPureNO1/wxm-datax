package com.wxm.security.util;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.wxm.base.dto.DataRtn;
import com.wxm.security.bean.MyUserDetails;
import com.wxm.security.bean.Token;
import com.wxm.security.encoder.SM3PasswordEncoder;
import com.wxm.util.my.MyUUIDUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/27 16:31
 * @since 1.0.0
 */
public class TokenUtil {
    /**4小时*/
    private static long EXP_TIME = 4 * 60 * 60 * 1000;
    public static   DataRtn getDataRtn(UserDetails userDetails, RedisTemplate redisTemplate,boolean checkRedis) {
        /**
         * UserDetails userDetails = userDetailsService.loadUserByUsername(tokenIn.getUsername());
         * userDetailsService.loadUserByUsername
         * 返回了 @see {org.springframework.security.core.userdetails.User}
         */

        User user = (User)userDetails;
        String userKey=getUserKey(user);


        Token token=null;
        if(checkRedis){
            token = redisTemplate.hasKey(userKey)&& ObjectUtils.isNotEmpty(redisTemplate.opsForValue().get(userKey))? (Token) redisTemplate.opsForValue().get(userKey) :null;
            if (!SecurityUtil.isEmptyToken(token)) {
                // 存在token 且未过期，直接返回
                if (System.currentTimeMillis() <= token.getExpiration()) {
                    // 重置redis时间
                    redisTemplate.expire(userKey,EXP_TIME,TimeUnit.MILLISECONDS);
                    redisTemplate.expire(token.getAccessToken(),EXP_TIME,TimeUnit.MILLISECONDS);
                    return new DataRtn().success().message("成功").data(token);
                }
            }
        }
        // 是否已经认证
        // 已经验证，返回token;没有验证，设置用户登录验证，然后返回token

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);



        token = new Token(MyUUIDUtil.uuid(user.getUsername()), System.currentTimeMillis() + EXP_TIME);
        //
        redisTemplate.opsForValue().set(userKey,token,EXP_TIME, TimeUnit.MILLISECONDS);
        // access_token 对应的授权用户信息
        // springsecurity 的 UserDetails 没有无参构造方法，Jacksion 序列化的时候会报错，存储自定义的
        // MyUserDetails
        MyUserDetails myUserDetails=new MyUserDetails()
                .setUsername(user.getUsername()).setPassword(user.getPassword())
                .setAccountNonLocked(user.isAccountNonLocked()).setAccountNonExpired(user.isAccountNonExpired())
                .setCredentialsNonExpired(user.isCredentialsNonExpired()).setEnabled(user.isEnabled())
                .setAuthorities(user.getAuthorities());
        redisTemplate.opsForValue().set(token.getAccessToken(), myUserDetails,EXP_TIME, TimeUnit.MILLISECONDS);
        return new DataRtn().success().message("成功").data(token);
    }

    public static String getUserKey(UserDetails user){
        return user.getUsername()+"-"+user.isAccountNonLocked()+"-"+user.isAccountNonExpired()+"-"+user.isEnabled()+"-"+user.isCredentialsNonExpired();
    }
}
