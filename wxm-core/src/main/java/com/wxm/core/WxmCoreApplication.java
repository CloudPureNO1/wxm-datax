package com.wxm.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ComponentScan(basePackages = {"com.wxm"})
@SpringBootApplication
public class WxmCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxmCoreApplication.class, args);
    }

}
