package com.wxm.datax.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/5/6 16:10
 * @since 1.0.0
 */
@Getter
@Setter
@Configuration
@PropertySource("classpath:application-datax.yml")
public class DataXPropertiesConfig {

    @Value("${sys.type}")
    private String sysType;
    @Value("${datax.home.path}")
    private String dataxHomePath;
    @Value("${datax.job.file.directory}")
    private String dataxJobFileDirectory;
}
