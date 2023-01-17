package com.wxm.security.oauth2.intercepter;

import com.alibaba.fastjson.JSON;
import com.wxm.druid.entity.master.WxmRole;
import com.wxm.security.oauth2.bean.MockData.MyRole;
import com.wxm.security.oauth2.encoder.SM3PasswordEncoder;
import com.wxm.service.db.master.IWxmRoleService;
import com.wxm.service.db.master.impl.WxmRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p></p>
 * <p>
 * 自定义权限过滤器，继承了 SecurityMetadataSource（权限资源接口），过滤所有请求，
 * 核查这个请求需要的访问权限；主要实现Collection<ConfigAttribute> getAttributes(Object o)方法，
 * 此方法中可编写用户逻辑，根据用户预先设定的用户权限列表，返回访问此url需要的权限列表。
 * </p>
 *
 * <p>
 * FilterInvocationSecurityMetadataSource（权限资源过滤器接口）继承了 SecurityMetadataSource（权限资源接口）
 * * Spring Security是通过SecurityMetadataSource来加载访问时所需要的具体权限；Metadata是元数据的意思。
 * * 自定义权限资源过滤器，实现动态的权限验证
 * * 它的主要责任就是当访问一个url时，返回这个url所需要的访问权限
 * </p>
 *
 * @author 王森明
 * @date 2021/4/28 10:36
 * @since 1.0.0
 */
@Slf4j
@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private static final String[] PERMISSION_ALL_URLS = {"/login","/auth/token","/refresh/token"};
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IWxmRoleService wxmRoleService;
    /**
     * 返回本次访问需要的权限，可以有多个权限
     *
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        try {
            String requestUrl = ((FilterInvocation) object).getRequestUrl().split("\\?")[0];

            //去数据库查询资源
            List<String> roleList = new ArrayList<>();
            List<WxmRole> listRole = wxmRoleService.queryByResourceUrl(requestUrl);
            if (!CollectionUtils.isEmpty(listRole)) {
                roleList = listRole.stream().map(role -> role.getRoleCode()).collect(Collectors.toList());
            }

            if (CollectionUtils.isEmpty(roleList)) {
                /**
                 * @如果本方法返回null的话，意味着当前这个请求不需要任何角色就能访问
                 * 此处做逻辑控制，如果没有匹配上的，返回一个默认具体权限，防止漏缺资源配置
                 **/
                if (Arrays.stream(PERMISSION_ALL_URLS).anyMatch(url -> url.equals(requestUrl))) {
                    return null;
                }
                log.info("当前访问路径是{},这个url所需要的访问权限是{}", requestUrl, "WXM_ROLE_LOGIN");
                return SecurityConfig.createList("WXM_ROLE_LOGIN");
            }
            log.info("当前访问路径是{},这个url所需要的访问权限是{}", requestUrl, JSON.toJSONString(roleList));
            return SecurityConfig.createList(roleList.toArray(new String[roleList.size()]));
        } catch (Exception e) {
            log.info(">>>>>校验资源权限发送异常：{}", e.getMessage(), e);
            return SecurityConfig.createList(new String[0]);
        }

    }

    /**
     * 此处方法如果做了实现，返回了定义的权限资源列表，
     * Spring Security会在启动时校验每个ConfigAttribute是否配置正确，
     * 如果不需要校验，这里实现方法，方法体直接返回null即可。
     *
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * 方法返回类对象是否支持校验，
     * web项目一般使用FilterInvocation来判断，或者直接返回true
     *
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
