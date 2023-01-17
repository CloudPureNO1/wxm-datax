package com.wxm.security.oauth2.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>跨域配置：方法有多种</p>
 * <p>https://blog.csdn.net/JokerLJG/article/details/12365938</p>
 *
 * @author 王森明
 * @date 2022/6/28 13:37
 * @since 1.0.0
 */
@Getter
@Setter
@Configuration
public class CrossConfig {
    @Value("${security.cross.origins}")
    private List<String>listOrigins=new ArrayList<>();
    /**
     * 当前跨域请求最大有效时长。这里默认1天
     */
    private static final long MAX_AGE = 24 * 60 * 60;
    @Bean
    CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        /**1 允许跨域的请求地址 例如 http://localhost:8090*/
        // configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedOrigins(listOrigins);
        /**2 允许跨域的请求头*/
        configuration.setAllowedMethods(Arrays.asList("*"));
        /**3 允许跨域的请求方法*/
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        /**当前跨域请求最大有效时长*/
        configuration.setMaxAge(MAX_AGE);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        /**4 配置接口跨域*/
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }


}
