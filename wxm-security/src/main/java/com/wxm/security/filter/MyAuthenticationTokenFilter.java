package com.wxm.security.filter;

import com.alibaba.fastjson.JSON;
import com.wxm.base.dto.DataRtn;
import com.wxm.base.enums.SecurityInfoEnum;
import com.wxm.security.bean.MyUserDetails;
import com.wxm.security.bean.Token;
import com.wxm.security.bean.TokenIn;
import com.wxm.security.encoder.SM3PasswordEncoder;
import com.wxm.security.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/4/26 14:59
 * @since 1.0.0
 */
@Slf4j
@Component
public class MyAuthenticationTokenFilter extends OncePerRequestFilter {
    /**
     * 直接放行的url,equals
     */
    private static final String[] PERMISSION_ALL_URLS = {"/login", "/auth/token", "/refresh/token"};
    /**
     * 放行的websocket地址,startWith
     */
    private static final String [] WEBSOCKET_URLS ={"/ws-push-socket"} ;
    @Value("${server.servlet.context-path}")
    private String contentPath;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        /**
         * 登录、获取token,刷新token 的地址排除token校验
         * 放行
         */
        String apiUrl = request.getRequestURI().replaceFirst(contentPath, "");
        log.info(">>>>>>>访问地址：{}", apiUrl);
        if (Arrays.stream(PERMISSION_ALL_URLS).anyMatch(url -> url.equals(apiUrl))) {
            filterChain.doFilter(request, response);
            return;
        }

        /**
         * 放开 websocket 连接
         * websocket 连接时不应该带有任何身份信息或验证信息
         * 需要校验，用其他比如，登录后才能连接（可以先登录，在连接websocket之间校验，校验通过的菜进行连接的代码）
         */
        if(Arrays.stream(WEBSOCKET_URLS).anyMatch(url->apiUrl.startsWith(url))){
            filterChain.doFilter(request, response);
            return;
        }


        String accessToken = request.getHeader("accessToken");
        log.info(">>>>>>>校验token：{}", apiUrl);
        /**
         * 没有传入token放行，交给后面的校验
         */
        if (!StringUtils.hasLength(accessToken)) {
            log.error("【{}】{}：{}", SecurityInfoEnum.TOKEN_EMPTY.getCode(), SecurityInfoEnum.TOKEN_EMPTY.getMsg(), SecurityInfoEnum.TOKEN_EMPTY.toString());
            DataRtn dataRtn = new DataRtn().setCode(SecurityInfoEnum.TOKEN_EMPTY.getCode()).message(SecurityInfoEnum.TOKEN_EMPTY.getMsg()).detailMsg(SecurityInfoEnum.TOKEN_EMPTY.getMsg());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json;charset=utf-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(JSON.toJSONString(dataRtn));
            return;
        }
        /**
         * Token 存储在redis中，如果redis中没有传入的token 提示token 无效
         */
        if (!redisTemplate.hasKey(accessToken)) {
            log.error("【{}】{}：{}", SecurityInfoEnum.TOKEN_EMPTY_ERROR.getCode(), SecurityInfoEnum.TOKEN_EMPTY_ERROR.getMsg(), SecurityInfoEnum.TOKEN_EMPTY_ERROR.toString());
            DataRtn dataRtn = new DataRtn().setCode(SecurityInfoEnum.TOKEN_EMPTY_ERROR.getCode()).message(SecurityInfoEnum.TOKEN_EMPTY_ERROR.getMsg()).detailMsg(SecurityInfoEnum.TOKEN_EMPTY_ERROR.getMsg());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json;charset=utf-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(JSON.toJSONString(dataRtn));
            return;
        }
        if (ObjectUtils.isEmpty(redisTemplate.opsForValue().get(accessToken))) {
            log.error("【{}】{}：{}", SecurityInfoEnum.TOKEN_EMPTY_ERROR.getCode(), SecurityInfoEnum.TOKEN_EMPTY_ERROR.getMsg(), SecurityInfoEnum.TOKEN_EMPTY_ERROR.toString());
            redisTemplate.delete(accessToken);
            DataRtn dataRtn = new DataRtn().setCode(SecurityInfoEnum.TOKEN_EMPTY_ERROR.getCode()).message(SecurityInfoEnum.TOKEN_EMPTY_ERROR.getMsg()).detailMsg(SecurityInfoEnum.TOKEN_EMPTY_ERROR.getMsg());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json;charset=utf-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(JSON.toJSONString(dataRtn));
            return;
        }


        UserDetails userDetails = (UserDetails) redisTemplate.opsForValue().get(accessToken);
        if (userDetails == null || !StringUtils.hasLength(userDetails.getUsername())) {
            log.error("【{}】{}：{}", SecurityInfoEnum.USER_LOGON_STATUS_INVALID.getCode(), SecurityInfoEnum.USER_LOGON_STATUS_INVALID.getMsg(), SecurityInfoEnum.USER_LOGON_STATUS_INVALID.toString());
            redisTemplate.delete(accessToken);
            DataRtn dataRtn = new DataRtn().setCode(SecurityInfoEnum.USER_LOGON_STATUS_INVALID.getCode()).message(SecurityInfoEnum.USER_LOGON_STATUS_INVALID.getMsg()).detailMsg(SecurityInfoEnum.USER_LOGON_STATUS_INVALID.getMsg());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json;charset=utf-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(JSON.toJSONString(dataRtn));
            return;
        }

        String userKey = TokenUtil.getUserKey(userDetails);
        if (!redisTemplate.hasKey(userKey)) {
            log.error("【{}】{}：{}", SecurityInfoEnum.USER_LOGON_STATUS_INVALID.getCode(), SecurityInfoEnum.USER_LOGON_STATUS_INVALID.getMsg(), SecurityInfoEnum.USER_LOGON_STATUS_INVALID.toString());
            redisTemplate.delete(accessToken);
            DataRtn dataRtn = new DataRtn().setCode(SecurityInfoEnum.USER_LOGON_STATUS_INVALID.getCode()).message(SecurityInfoEnum.USER_LOGON_STATUS_INVALID.getMsg()).detailMsg(SecurityInfoEnum.USER_LOGON_STATUS_INVALID.getMsg());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json;charset=utf-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(JSON.toJSONString(dataRtn));
            return;
        }
        if (ObjectUtils.isEmpty(redisTemplate.opsForValue().get(userKey))) {
            log.error("【{}】{}：{}", SecurityInfoEnum.USER_LOGON_STATUS_INVALID.getCode(), SecurityInfoEnum.USER_LOGON_STATUS_INVALID.getMsg(), SecurityInfoEnum.USER_LOGON_STATUS_INVALID.toString());
            redisTemplate.delete(accessToken);
            redisTemplate.delete(userKey);
            DataRtn dataRtn = new DataRtn().setCode(SecurityInfoEnum.USER_LOGON_STATUS_INVALID.getCode()).message(SecurityInfoEnum.USER_LOGON_STATUS_INVALID.getMsg()).detailMsg(SecurityInfoEnum.USER_LOGON_STATUS_INVALID.getMsg());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json;charset=utf-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(JSON.toJSONString(dataRtn));
            return;
        }
        Token token = (Token) redisTemplate.opsForValue().get(userKey);
        log.info(">>>>>>>token校验通过：继续执行");
        if (System.currentTimeMillis() <= token.getExpiration()) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
        } else {
            log.error("【{}】{}：{}", SecurityInfoEnum.TOKEN_INVALID.getCode(), SecurityInfoEnum.TOKEN_INVALID.getMsg(), SecurityInfoEnum.TOKEN_INVALID.toString());
            redisTemplate.delete(accessToken);
            redisTemplate.delete(userKey);
            DataRtn dataRtn = new DataRtn().setCode(SecurityInfoEnum.TOKEN_INVALID.getCode()).message(SecurityInfoEnum.TOKEN_INVALID.getMsg()).detailMsg(SecurityInfoEnum.TOKEN_INVALID.getMsg());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json;charset=utf-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(JSON.toJSONString(dataRtn));
            return;
        }
        filterChain.doFilter(request, response);
    }

}
