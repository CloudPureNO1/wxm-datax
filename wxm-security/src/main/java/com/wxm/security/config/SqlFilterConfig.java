package com.wxm.security.config;

import com.alibaba.fastjson.JSON;
import com.wxm.security.filter.SqlFilter;
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
@ConfigurationProperties(prefix = "security.inject.sql",ignoreInvalidFields = true)
public class SqlFilterConfig {
    private String IllegalCharacter;
    private String urlPatterns;
    private String ignoreUrl;
    private String needVerifyMediaTypes;
    private String unfriendlyApi;
    private String base64EncodeApi;
    private List<Map<String,String>>ignoreConfig=new ArrayList<>();


    private Map<String, String> initParameters = new HashMap<>();

    @Bean
    public SqlFilter sqlFilter() {
        return new SqlFilter();
    }

    @Bean
    public FilterRegistrationBean sqlFilterRegistration() {
        initParameters.put("sqlIgnoreUrl", ignoreUrl);
        initParameters.put("IllegalCharacter", IllegalCharacter);
        initParameters.put("needVerifyMediaTypes", needVerifyMediaTypes);
        initParameters.put("unfriendlyApi", unfriendlyApi);
        initParameters.put("base64EncodeApi", base64EncodeApi);
        initParameters.put("ignoreConfig", JSON.toJSONString(ignoreConfig));

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(sqlFilter());
        filterRegistrationBean.setName("sqlFilter");
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        filterRegistrationBean.addUrlPatterns(urlPatterns);
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }

}
