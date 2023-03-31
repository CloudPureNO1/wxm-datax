package com.wxm.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackages = {"com.wxm.test.main"})
@SpringBootApplication
public class WxmZTestApplication {

    public static void main(String[] args) {

        SpringApplication.run(WxmZTestApplication.class,
                args);
    }

}
