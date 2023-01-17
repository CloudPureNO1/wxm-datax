package com.wxm.security.oauth2.config;

import com.wxm.security.oauth2.handler.MyAccessDeniedHandler;
import com.wxm.security.oauth2.intercepter.MyAccessDecisionManager;
import com.wxm.security.oauth2.intercepter.MyFilterInvocationSecurityMetadataSource;
import com.wxm.security.oauth2.point.MyTokenExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/4/28 17:12
 * @since 1.0.0
 */
@Configuration
@EnableResourceServer
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter {
    /**token 判断*/
    private final MyTokenExceptionEntryPoint myTokenExceptionEntryPoint;
    /** 登录认证用户无权限访问处理 */
    private final MyAccessDeniedHandler myAccessDeniedHandler;

    /**
     * 自定义权限决策管理器  先查询此用户当前拥有的权限，然后与上面(MyFilterInvocationSecurityMetadataSource)过滤器核查出来的权限列表作对比，
     */
    private final MyAccessDecisionManager myAccessDecisionManager;
    /**
     * 自定义权限过滤器，继承了 SecurityMetadataSource（权限资源接口），过滤所有请求，
     * 核查获取这个请求需要的访问权限
     */
    private final MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;

    public MyResourceServerConfig(@Qualifier("myTokenExceptionEntryPoint") MyTokenExceptionEntryPoint myTokenExceptionEntryPoint, MyAccessDeniedHandler myAccessDeniedHandler, MyAccessDecisionManager myAccessDecisionManager, MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource) {
        this.myTokenExceptionEntryPoint = myTokenExceptionEntryPoint;
        this.myAccessDeniedHandler = myAccessDeniedHandler;
        this.myAccessDecisionManager = myAccessDecisionManager;
        this.myFilterInvocationSecurityMetadataSource = myFilterInvocationSecurityMetadataSource;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // token失效处理器
        resources.authenticationEntryPoint(myTokenExceptionEntryPoint);
        //权限异常处理类 没有生效
        resources.accessDeniedHandler(myAccessDeniedHandler);
        // 设置资源id  通过client的 scope 来判断是否具有资源权限
        resources.resourceId("rid");
    }

    /**
     * 这里设置需要token验证的url
     * 可以在WebSecurityConfigurerAdapter中排除掉，
     * 对于相同的url，如果二者都配置了验证
     * 则优先进入ResourceServerConfigurerAdapter,进行token验证。而不会进行
     * WebSecurityConfigurerAdapter 的 basic auth或表单认证。
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                /**
                 * 对资源的权限判断应该动态的放在元数据中判断，
                 * 对于方法访问权限  @EnableGlobalMethodSecurity(prePostEnabled = true)  // 可以进行方法校验  校验资源权限
                 * 配合  @PreAuthorize("hasAuthority('admin')")   使用
                 */
                /*              .antMatchers("/admin/**").hasRole("admin")
                              .antMatchers("/user/**").hasRole("user")*/
                //.antMatchers("/login/oauth/token").permitAll()
                .anyRequest().authenticated()
                .and()
                .authorizeRequests()
                /**
                 * 重写做权限判断
                 */
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        // o.setAccessDecisionManager(myAccessDecisionManager);      // 权限判断
                        o.setAccessDecisionManager(myAccessDecisionManager);//访问决策管理器
                        o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);//安全元数据源
                        return o;
                    }
                });

    }
}
