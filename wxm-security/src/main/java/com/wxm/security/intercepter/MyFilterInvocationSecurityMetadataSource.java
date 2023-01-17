package com.wxm.security.intercepter;

import com.alibaba.fastjson.JSON;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.master.WxmApi;
import com.wxm.druid.entity.master.WxmRole;
import com.wxm.security.bean.MockData.MyRole;
import com.wxm.security.bean.MyUserDetails;
import com.wxm.security.encoder.SM3PasswordEncoder;
import com.wxm.service.db.master.IWxmApiService;
import com.wxm.service.db.master.IWxmRoleService;
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
    private static final String SUPER_SUPER_MANAGER = "wxm";
    private static final String[] PERMISSION_ALL_URLS = {"/login", "/auth/token", "/refresh/token"};
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IWxmRoleService wxmRoleService;
    @Autowired
    private IWxmApiService wxmApiService;

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
            boolean bLogon = false;
            List<WxmRole> listR = null;
            String username = null;
            String requestUrl = ((FilterInvocation) object).getRequestUrl().split("\\?")[0];
            // 已经登录，且登录用户名为wxm 自己的超超级管理员，直接放行
            String accessToken = ((FilterInvocation) object).getHttpRequest().getHeader("accessToken");
            if (StringUtils.hasLength(accessToken)) {
                if (redisTemplate.hasKey(accessToken)) {
                    MyUserDetails myUserDetails = (MyUserDetails) redisTemplate.opsForValue().get(accessToken);
                    // UserDetails userDetails = (UserDetails) redisTemplate.opsForValue().get(accessToken);
                     /*   Authentication authentication = new UsernamePasswordAuthenticationToken(myUserDetails, null, myUserDetails.getAuthorities());
                        SecurityContext securityContext = SecurityContextHolder.getContext();
                        securityContext.setAuthentication(authentication);*/
                    if (StringUtils.hasLength(myUserDetails.getUsername()) && SUPER_SUPER_MANAGER.equals(myUserDetails.getUsername())) {
                        // 放行
                        return new ArrayList<>();
                    }


                    if (StringUtils.hasLength(myUserDetails.getUsername())) {
                        bLogon = true;
                        username = myUserDetails.getUsername();
                        listR = wxmRoleService.findRolesByUsername(myUserDetails.getUsername());
                        if (CollectionUtils.isEmpty(listR)) {
                            log.info(">>>>>请先配置角色：>>>>>>>>{}", requestUrl);
                            return SecurityConfig.createList("WXM_ROLE_NEED");
                        }
                    }

                }
            }

            if (StringUtils.hasLength(username)) {
                List<WxmApi> listApi = wxmApiService.queryByUsername(username);
                if (!CollectionUtils.isEmpty(listApi)) {
                    String[] a1 = requestUrl.split("/");
                    for (WxmApi api : listApi) {
                        if (api.getApiUrl().equals(requestUrl)) {
                            return null;
                        }

                        String[] a2 = api.getApiUrl().split("/");
                        if(a1.length==a2.length&&a1[0].equals(a2[0])){
                            boolean bFlag=true;
                            for(int i=1;i<a2.length; i++){
                                if(a2[i].startsWith("{")&&a2[i].endsWith("}")){
                                    continue;
                                }
                                if(a2[i].equals(a1[i])){
                                    continue;
                                }
                                bFlag=false;
                                break;
                            }
                            if(bFlag){
                                // 放行
                                return null;
                            }
                            continue;
                        }
                    }
                }
            }


            //去数据库查询资源
            List<String> roleList = new ArrayList<>();
            // 这里请求的是接口的地址，不是资源和权限表的地址，采用wxm_api表
            // List<WxmRole> listRole = wxmRoleService.queryByResourceUrl(requestUrl);
            List<WxmRole> listRole = wxmRoleService.queryByApiUrl(requestUrl);
            if (!CollectionUtils.isEmpty(listRole)) {
                if (!CollectionUtils.isEmpty(listR) && listR.stream().anyMatch(role -> listRole.stream().anyMatch(r -> role.getRoleCode().equals(r.getRoleCode())))) {
                    // 用户用户的角色和资源所在的角色符合，直接放行
                    return null;
                }

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
                if (bLogon) {
                    log.error(">>>>>请先配置接口权限：>>>>>>>>{}", requestUrl);
                    return SecurityConfig.createList("WXM_ROLE_NEED");
                }
                log.error(">>>>>请先登录或获取token：>>>>>>>>");
                return SecurityConfig.createList("WXM_ROLE_LOGIN");
            }
            log.info("当前访问路径是{},这个url所需要的访问权限是{}", requestUrl, JSON.toJSONString(roleList));
            return SecurityConfig.createList(roleList.toArray(new String[roleList.size()]));
        } catch (Exception e) {
            log.error(">>>>>校验资源权限发生异常：{}", e.getMessage(), e);
            return SecurityConfig.createList("WXM_ROLE_CHECK_EXC");
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
