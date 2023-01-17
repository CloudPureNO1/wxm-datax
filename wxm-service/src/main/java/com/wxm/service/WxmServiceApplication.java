package com.wxm.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ComponentScan(basePackages = {"com.wxm"})
@SpringBootApplication
public class WxmServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxmServiceApplication.class, args);
    }

}
