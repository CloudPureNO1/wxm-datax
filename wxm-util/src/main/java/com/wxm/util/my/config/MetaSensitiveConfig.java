package com.wxm.util.my.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 元数据脱敏配置
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-12 14:29:39
 */
@Configuration
@ConfigurationProperties(prefix = "rs.config.meta.sensitive")
public class MetaSensitiveConfig {
    private String cron="0 0 23 * * ?";
    private String encryptClass="com.insigma.soft.bigdata.utils.AESUtil";
    private String encryptMethod="encrypt";
    private String decryptMethod="decrypt";
    private List<Map<String, Object>> list = new ArrayList<>();


    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getEncryptClass() {
        return encryptClass;
    }

    public void setEncryptClass(String encryptClass) {
        this.encryptClass = encryptClass;
    }

    public String getEncryptMethod() {
        return encryptMethod;
    }

    public void setEncryptMethod(String encryptMethod) {
        this.encryptMethod = encryptMethod;
    }

    public String getDecryptMethod() {
        return decryptMethod;
    }

    public void setDecryptMethod(String decryptMethod) {
        this.decryptMethod = decryptMethod;
    }
}
