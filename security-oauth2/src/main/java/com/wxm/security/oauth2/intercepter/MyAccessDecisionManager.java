package com.wxm.security.oauth2.intercepter;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * <p>自定义权限决策管理器</p>
 * <p>
 *     自定义权限决策管理器，需要实现AccessDecisionManager 的
 *     void decide(Authentication auth, Object object, Collection<ConfigAttribute> cas) 方法，
 *     在上面的过滤器中，我们已经得到了访问此url需要的权限；
 *     那么，decide方法，先查询此用户当前拥有的权限，然后与上面过滤器核查出来的权限列表作对比，
 *     以此判断此用户是否具有这个访问权限，决定去留！所以顾名思义为权限决策器。
 * </p>
 * <p>
 * 有了权限资源(MyFilterInvocationSecurityMetadataSource)，知道了当前访问的url需要的具体权限，接下来就是决策当前的访问是否能通过权限验证了
 * MyAccessDecisionManager 自定义权限决策管理器
 * </p>
 * @author 王森明
 * @date 2021/4/28 10:45
 * @since 1.0.0
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    /**
     * 取当前用户的权限与这次请求的这个url需要的权限作对比，决定是否放行
     * authentication 包含了当前的用户信息，包括拥有的权限,即之前UserDetailsService登录时候存储的用户对象
     * object 就是FilterInvocation对象，可以得到request等web资源。
     * 是本次访问需要的权限。即上一步的 MyFilterInvocationSecurityMetadataSource 中查询核对得到的权限列表
     * @param authentication
     * @param object
     * @param configAttributes
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()) {
            if (authentication == null) {throw new AccessDeniedException("很抱歉，您没有该访问权限");}
            ConfigAttribute configAttribute = iterator.next();
            //当前请求需要的权限
            String needRole = configAttribute.getAttribute();

            //当前用户所具有的权限
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    return;  //放行
                }
                if("ROLE_ANONYMOUS".equals(authority.getAuthority())){
                    if("WXM_ROLE_TOKEN".equals(needRole)){
                        return ; // 放行token,permitAll的token
                    }
                }
            }

            // 其他未配置权限的资源
            if ("WXM_ROLE_LOGIN".equals(needRole)) {
                // 如果是 没有查询到需要的权限（MTMS_ROLE_LOGIN） 默认需要登录权限，这时候如果授权为AnonymousAuthenticationToken，提示未登录
                // 所以，获取token的接口，除了设置permitAll ,还要有对应的权限
                if (authentication instanceof AnonymousAuthenticationToken) {
                    // throw new BadCredentialsException("未登录验证");
                    throw new BadCredentialsException("未完全身份验证");
                } else{
                    return;  //放行，没有权限的资源，登录状态下可以访问
                }
            }

        }
        throw new AccessDeniedException("很抱歉，您没有该访问权限!");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
