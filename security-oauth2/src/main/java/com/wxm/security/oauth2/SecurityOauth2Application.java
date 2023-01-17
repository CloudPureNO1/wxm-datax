package com.wxm.security.oauth2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ComponentScan(basePackages = {"com.wxm"})
@MapperScan(basePackages = {"com.wxm.druid.mapper"})
@SpringBootApplication
public class SecurityOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(SecurityOauth2Application.class, args);
    }

}
