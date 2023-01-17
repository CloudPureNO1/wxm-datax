package com.wxm.security.oauth2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxm.druid.entity.master.oauth2.OauthClientDetails;
import com.wxm.security.oauth2.encoder.SM3PasswordEncoder;
import com.wxm.service.db.master.oauth2.IOauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/4/29 13:52
 * @since 1.0.0
 */

public class MyClientDetailsServiceImpl implements ClientDetailsService {
    @Autowired
    private SM3PasswordEncoder sm3PasswordEncoder;
    @Autowired
    private IOauthClientDetailsService oauthClientDetailService;
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        /**
         * 模拟查询数据库获取数据
         * throw new BadCredentialsException("哈哈哈，异常了");  直接抛出只能改变提示信息的值，确不能改变返回的key等格式
         * 所以
         * 需要,在此次MyClientCredentialsTokenEndpointFilter 重写 client_id 和client_secret 密码的错误
         * MyAuthenticationEntryPoint  为最终返回格式
         */
        QueryWrapper<OauthClientDetails>queryWrapper =new QueryWrapper<>();
        List<OauthClientDetails> clientsList=oauthClientDetailService.list(queryWrapper.eq("client_id",clientId));
        if(CollectionUtils.isEmpty(clientsList)){
            throw new BadCredentialsException("客户端信息不存在");
        }
        if(clientsList.size()>1){
            throw new BadCredentialsException("存在多条客户端信息");
        }
        OauthClientDetails clientModel=clientsList.get(0);
        BaseClientDetails details = new BaseClientDetails();

        // 客户端ID
        details.setClientId(clientId);

        // 客户端访问密匙
        //details.setClientSecret(sm3PasswordEncoder.encode("mtms-wxm-2021-secret"));
        //details.setClientSecret(clientModel.getClientSecret());
        details.setClientSecret(sm3PasswordEncoder.encode(clientModel.getClientSecret()));

        // 客户端支持的授权许可类型(grant_type)，可选值包括authorization_code,password,refresh_token,implicit,client_credentials,若支持多个授权许可类型用逗号(,)分隔
        //details.setAuthorizedGrantTypes(Arrays.asList("authorization_code", "password", "refresh_token"));
        //details.setAuthorizedGrantTypes(Arrays.asList(clientModel.getAuthorizedGrantTypes()));
        details.setAuthorizedGrantTypes(Arrays.asList(clientModel.getAuthorizedGrantTypes().split(",")));

        //客户端申请的权限范围，可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔
        //details.setScope(Arrays.asList("read", "trust", "write"));
        // details.setScope(Arrays.asList(clientModel.getScope()));
        details.setScope(Arrays.asList(clientModel.getScope().split(",")));

        // 客户端所能访问的资源id集合，多个资源时用逗号(,)分隔
        //details.setResourceIds(Arrays.asList("oauth2-resource", "rid"));
        // details.setResourceIds(Arrays.asList(clientModel.getResourceIds()));
        details.setResourceIds(Arrays.asList(clientModel.getResourceIds().split(",")));

        // 客户端重定向URI，当grant_type为authorization_code或implicit时, 在Oauth的流程中会使用并检查与数据库内的redirect_uri是否一致
        //details.setRegisteredRedirectUri(Collections.singleton("http://anywhere.com"));
        details.setRegisteredRedirectUri(Collections.singleton(clientModel.getWebServerRedirectUri()));

        // 设定客户端的access_token的有效时间值(单位:秒)，若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时)
        //details.setAccessTokenValiditySeconds(60 * 60 * 4);
        details.setAccessTokenValiditySeconds(clientModel.getAccessTokenValidity());

        // 设定客户端的refresh_token的有效时间值(单位:秒)，若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天)
        //details.setRefreshTokenValiditySeconds(60 * 60 * 24);
        details.setRefreshTokenValiditySeconds(clientModel.getRefreshTokenValidity());

        // 客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔
/*        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
        details.setAuthorities(authorities);*/

        // 设置用户是否自动批准授予权限操作, 默认值为 ‘false’, 可选值包括 ‘true’,‘false’, ‘read’,‘write’.
        //details.setAutoApproveScopes(Arrays.asList("false"));
        return details;


    }
}
