package com.wxm.api;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author wangsm
 */
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.wxm" })
// @MapperScan(basePackages = {"com.wxm.druid.mapper"})
@SpringBootApplication(exclude = { DruidDataSourceAutoConfigure.class})
public class WxmApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxmApiApplication.class, args);
    }

}
