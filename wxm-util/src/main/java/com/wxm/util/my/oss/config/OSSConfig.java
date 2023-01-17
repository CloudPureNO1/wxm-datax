package com.wxm.util.my.oss.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/10/21 10:19
 * @since 1.0.0
 */
@Configuration
@ConfigurationProperties(prefix="wxm.oss")
// 在spring boot中有时候需要控制配置类是否生效,可以使用@ConditionalOnProperty注解来控制@Configuration是否生效.
@ConditionalOnProperty(
        prefix = "wxm.active",
        name = {"fs"},
        havingValue = "oss"
)
@Data
public class OSSConfig {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
}
