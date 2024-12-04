package com.wxm.quartz;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ComponentScan(basePackages = {"com.wxm"})
@MapperScan(basePackages = {"com.wxm.**.*.mapper"})
public class WxmQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxmQuartzApplication.class, args);
    }

}
