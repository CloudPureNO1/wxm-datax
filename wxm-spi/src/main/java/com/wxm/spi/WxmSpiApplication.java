package com.wxm.spi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class WxmSpiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxmSpiApplication.class, args);
    }

}
