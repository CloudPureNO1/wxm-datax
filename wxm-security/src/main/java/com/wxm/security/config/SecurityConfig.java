package com.wxm.security.config;

import com.wxm.security.encoder.SM3PasswordEncoder;
import com.wxm.security.filter.MyAuthenticationTokenFilter;
import com.wxm.security.handler.MyAccessDeniedHandler;
import com.wxm.security.handler.MyAuthenticationFailureHandler;
import com.wxm.security.handler.MyAuthenticationSuccessHandler;
import com.wxm.security.handler.MyLogoutSuccessHandler;
import com.wxm.security.intercepter.MyAccessDecisionManager;
import com.wxm.security.intercepter.MyFilterInvocationSecurityMetadataSource;
import com.wxm.security.point.MyAuthenticationEntryPoint;
import com.wxm.security.service.impl.UserDetailsServiceImpl;
import com.wxm.security.strategy.MySessionInformationExpiredStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/4/25 14:28
 * @since 1.0.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 登录成功
     */
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    /**
     * 登录失败
     */
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    /**
     * 退出成功
     */
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    /**
     * 异常处理(权限拒绝、登录失效等)  匿名用户访问无权限资源时的异常处理,返回JSON，而不是登录界面
     */
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    /**
     * 会话信息过期策略会话信息过期策略(账号被挤下线)
     */
    @Autowired
    private MySessionInformationExpiredStrategy customizeSessionInformationExpiredStrategy;

    /**
     * 根据token访问资源
     */
    @Autowired
    private MyAuthenticationTokenFilter myAuthenticationTokenFilter;

    /**
     * 登录认证用户无权限访问处理
     */
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    /**
     * 自定义权限过滤器，继承了 SecurityMetadataSource（权限资源接口），过滤所有请求，
     * 核查获取这个请求需要的访问权限
     */
    @Autowired
    private MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;

    /**
     * 自定义权限决策管理器  先查询此用户当前拥有的权限，然后与上面(MyFilterInvocationSecurityMetadataSource)过滤器核查出来的权限列表作对比，
     */
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;

    /**
     * 注册密码加密
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SM3PasswordEncoder();
    }

    /**
     * 注册用户实体
     *
     * @return
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    /**
     * 使用自定义登录身份认证组件
     * 配置认证方式   声明 UserDetailServiceImpl
     * SM3PasswordEncoder 自定义加密算法
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new SM3PasswordEncoder());
    }

    /**
     * 配置不拦截的规则
     * 静态资源
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //设置静态资源不要拦截
        web.ignoring().antMatchers("/js/**", "/cs/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@PreAuthorize("hasAuthority('admin')"), 只能针对方法校验，用在方法上
        // 对于其他的非法url 需要用元数据校验

        //http.httpBasic() // HTTP Basic方式
        // 禁用 csrf,不热post不能用
        http.csrf().disable();
        // 授权配置
        http.authorizeRequests().
                withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(myAccessDecisionManager);//访问决策管理器
                        o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);//安全元数据源
                        return o;
                    }
                })
                // 跨域预检请求  OPTIONS请求全部放行
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 自定义token 采用token校验
                .antMatchers("/auth/token").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/validateCode/**").permitAll()
                .antMatchers("/wxmApi/login").permitAll()
                .antMatchers("/wxmApi/validateCode/**").permitAll()
                //异常处理(权限拒绝、登录失效等)
                .and().exceptionHandling()
                //匿名用户访问无权限资源时的异常处理
                .authenticationEntryPoint(myAuthenticationEntryPoint)
                //认证用户访问无权限资源时处理
                .accessDeniedHandler(myAccessDeniedHandler)
                // 表单方式 登录  允许所有用户
                .and().formLogin().loginPage("/login").permitAll()
                // 登录成功处理逻辑
                .successHandler(myAuthenticationSuccessHandler)
                // 登录失败处理逻辑
                .failureHandler(myAuthenticationFailureHandler)


                //退出  允许所有用户
                .and().logout().logoutUrl("/logout").permitAll()
                //登出成功处理逻辑
                .logoutSuccessHandler(myLogoutSuccessHandler)
                //登出之后删除cookie
                .deleteCookies("JSESSIONID")

                //所有请求 都需要认证
                .and().authorizeRequests().anyRequest().authenticated()

                // 取消session  采用token验证时，使用 使用JWT，所以不需要HttpSession
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 因为采用token 过期策略和token校验，所以不需要使用session
//                .and().sessionManagement()
//                .maximumSessions(1)//同一账号同时登录最大用户数
//                .expiredSessionStrategy(customizeSessionInformationExpiredStrategy);//会话信息过期策略会话信息过期策略(账号被挤下线)

        http.addFilterBefore(myAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);//增加到默认拦截链中
    }
}
