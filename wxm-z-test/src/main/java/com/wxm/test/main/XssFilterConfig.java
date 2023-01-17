package com.wxm.test.main;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/8/8 17:18
 * @since 1.0.0
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "security.script.xss",ignoreInvalidFields = true)
public class XssFilterConfig {
    private String IllegalCharacter;
    private String urlPatterns;
    private String ignoreUrl;
    private String needVerifyMediaTypes;
    private String unfriendlyApi;


    private Map<String, String> initParameters = new HashMap<>();

    @Bean
    public XssFilter xssFilter() {
        return new XssFilter();
    }

    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        initParameters.put("sqlIgnoreUrl", ignoreUrl);
        initParameters.put("IllegalCharacter", IllegalCharacter);
        initParameters.put("needVerifyMediaTypes", needVerifyMediaTypes);
        initParameters.put("unfriendlyApi", unfriendlyApi);

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(xssFilter());
        filterRegistrationBean.setName("xssFilter");
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        filterRegistrationBean.addUrlPatterns(urlPatterns);
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }

}
