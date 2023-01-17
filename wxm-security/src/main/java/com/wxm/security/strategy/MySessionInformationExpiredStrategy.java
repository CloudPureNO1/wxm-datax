package com.wxm.security.strategy;

import com.alibaba.fastjson.JSON;
import com.wxm.base.dto.DataRtn;
import com.wxm.base.enums.SecurityInfoEnum;
import com.wxm.security.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>处理账号被挤下线处理逻辑  会话信息过期策略</p>
 * <p>同样的，当账号异地登录导致被挤下线时也要返回给前端json格式的数据，比如提示"账号下线"、"您的账号在异地登录，
 * 是否是您自己操作"或者"您的账号在异地登录,可能由于密码泄露，建议修改密码"等。这时就要实现SessionInformationExpiredStrategy
 * （会话信息过期策略）来自定义会话过期时的处理逻辑。
 * </p>
 *
 * @author 王森明
 * @date 2021/4/1 17:12
 * @since 1.0.0
 */
@Slf4j
@Component
public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        log.info(">>>>>>>>账号下线：{}",SecurityInfoEnum.USER_ACCOUNT_LOGON_BY_OTHERS.getMsg());
        HttpServletRequest httpServletRequest = sessionInformationExpiredEvent.getRequest();
        String accessToken = httpServletRequest.getHeader("accessToken");
        if(StringUtils.hasLength(accessToken)&&redisTemplate.hasKey(accessToken)){
            UserDetails userDetails = (UserDetails) redisTemplate.opsForValue().get(accessToken);
            if(userDetails != null&&StringUtils.hasLength(userDetails.getUsername())){
                String userKey = TokenUtil.getUserKey(userDetails);
                if(redisTemplate.hasKey(userKey)){
                    redisTemplate.delete(userKey);
                }
            }
            redisTemplate.delete(accessToken);
        }

        HttpServletResponse httpServletResponse = sessionInformationExpiredEvent.getResponse();
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(new DataRtn<>().setCode(SecurityInfoEnum.USER_ACCOUNT_LOGON_BY_OTHERS.getCode()).message(SecurityInfoEnum.USER_ACCOUNT_LOGON_BY_OTHERS.getMsg())));
    }
}
