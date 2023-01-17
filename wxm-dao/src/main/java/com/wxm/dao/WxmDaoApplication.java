package com.wxm.dao;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.wxm"})
@MapperScan(basePackages = {"com.wxm.dao.mapper"})
@SpringBootApplication
public class WxmDaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxmDaoApplication.class, args);
    }

}
