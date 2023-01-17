package com.wxm.quartz.single;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ComponentScan(basePackages = {"com.wxm"})
@MapperScan(basePackages = {"com.wxm.quartz.single.mapper"})
@SpringBootApplication
public class QuartzSingleApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzSingleApplication.class, args);
    }

}
