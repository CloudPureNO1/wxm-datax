package com.wxm.security.config;

import com.alibaba.fastjson.JSON;
import com.wxm.security.filter.XssFilter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private String base64EncodeApi;
    private List<Map<String,String>> ignoreConfig=new ArrayList<>();


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
        initParameters.put("base64EncodeApi", base64EncodeApi);
        initParameters.put("ignoreConfig", JSON.toJSONString(ignoreConfig));

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(xssFilter());
        filterRegistrationBean.setName("xssFilter");
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        filterRegistrationBean.addUrlPatterns(urlPatterns);
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }

}
