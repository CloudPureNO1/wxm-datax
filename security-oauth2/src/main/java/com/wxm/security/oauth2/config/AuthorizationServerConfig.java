package com.wxm.security.oauth2.config;

import com.wxm.security.oauth2.point.MyAuthenticationEntryPoint;
import com.wxm.security.oauth2.service.impl.MyClientDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * <p></p>
 * <p>@EnableResourceServer会给Spring Security的FilterChan添加一个OAuth2AuthenticationProcessingFilter过滤器，过滤所有的资源请求。
 * OAuth2AuthenticationProcessingFilter会使用OAuth2AuthenticationManager来验证token。验证Token的时候会去oauth_client_details表加载client配置信息。
 * 在Spring Security的FilterChain中，OAuth2AuthenticationProcessingFilter在FilterSecurityInterceptor的前面，所以会先验证client有没有此resource的权限，只有在有此resource的权限的情况下，才会再去做进一步的进行其他验证的判断。
 *
 * @author 王森明
 * @date 2021/4/29 9:45
 * @since 1.0.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final RedisConnectionFactory redisConnectionFactory;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    private final TokenEnhancer tokenEnhancer;

    private final MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    private final ClientCredentialsTokenEndpointFilter myClientCredentialsTokenEndpointFilter;



    public AuthorizationServerConfig(AuthenticationManager authenticationManager, RedisConnectionFactory redisConnectionFactory, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, @Qualifier("mytTokenEnhancer") TokenEnhancer tokenEnhancer, @Qualifier("myAuthenticationEntryPoint") MyAuthenticationEntryPoint myAuthenticationEntryPoint, ClientCredentialsTokenEndpointFilter myClientCredentialsTokenEndpointFilter) {
        this.authenticationManager = authenticationManager;
        this.redisConnectionFactory = redisConnectionFactory;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.tokenEnhancer = tokenEnhancer;
        this.myAuthenticationEntryPoint = myAuthenticationEntryPoint;
        this.myClientCredentialsTokenEndpointFilter = myClientCredentialsTokenEndpointFilter;
    }

    /**
     * 授权服务器和资源服务器必须一直，否则资料服务器调用的时候，回包token错误，（因为从不同存储的token导致）
     * token  采用Redis 存储
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTokenStore redisTokenStore(RedisConnectionFactory redisConnectionFactory) {
        return new RedisTokenStore(redisConnectionFactory);
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        /**
         * 自定义客户端返回格式 需要注释掉 security.allowFormAuthenticationForClients(); ，否则不起作用
         */
        myClientCredentialsTokenEndpointFilter.afterPropertiesSet();
        myClientCredentialsTokenEndpointFilter.setAuthenticationEntryPoint(myAuthenticationEntryPoint);
        security.addTokenEndpointAuthenticationFilter(myClientCredentialsTokenEndpointFilter);

        // 允许通过form提交客户端认证信息(client_id,client_secret),默认为basic方式认证
        // security.allowFormAuthenticationForClients();
        /**
         * security.checkTokenAccess("isAuthenticated()");    设置 "/oauth/check_token"端点默认不允许访问
         * security.tokenKeyAccess("isAuthenticated()");       设置 "/oauth/token_key"断点默认不允许访问
         */
        // 配置密码编码器
        security.passwordEncoder(passwordEncoder);




    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 注册自定义客户端信息服务
        clients.withClientDetails(new MyClientDetailsServiceImpl());

    }



    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 集成SpringSecurity认证  开启密码授权类型
        endpoints.authenticationManager(authenticationManager)
                // 注册redis令牌仓库
                .tokenStore(redisTokenStore(redisConnectionFactory))
                // 自定义用户实现
                .userDetailsService(userDetailsService)
                // 自定义token实现
                .tokenEnhancer(tokenEnhancer);

    }
}
