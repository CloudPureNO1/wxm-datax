package com.wxm.service.biz.mall;

import com.alibaba.fastjson.JSON;
import com.wxm.base.exception.BizSvcException;
import com.wxm.base.exception.DbSvcException;
import com.wxm.util.my.MyStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-29 18:16:46
 */
@Service
public class MallLoader {
    private static final String DTO_IN_PREFIX="com.wxm.base.dto.rbac.in.Mall";
    private static final String DTO_IN_SUFFIX="In";
    private final ApplicationContext applicationContext;
    private Map<String, MallStrategy<?, ?>> apiStrategies;

    @Autowired
    public MallLoader(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.apiStrategies = new HashMap<>();
        initApiStrategies();
    }

    private void initApiStrategies() {
        // 获取所有的ApiStrategy Bean
        Map<String, MallStrategy> strategies = applicationContext.getBeansOfType(MallStrategy.class);
        for (Map.Entry<String, MallStrategy> entry : strategies.entrySet()) {
            String beanName = entry.getKey();
            MallStrategy<?, ?> strategy = entry.getValue();

            // 解析Bean名称以提取API ID
            String apiId = parseApiIdFromBeanName(beanName);
            apiStrategies.put(apiId, strategy);
        }
    }

    private String parseApiIdFromBeanName(String beanName) {
        // 假设Bean名称为"api1001Strategy"
        String apiIdPart = beanName.replaceFirst("mall", "").replaceAll("Strategy", "");
        return apiIdPart;
    }

    public <T, R> R execute(String transCode, String jsonText, HttpServletRequest request) throws DbSvcException, BizSvcException {
        MallStrategy<T, R> strategy = (MallStrategy<T, R>) apiStrategies.get(transCode);
        if (strategy == null) {
            throw new IllegalArgumentException("交易编号 " + transCode + " not found");
        }
        Class<T>dtoClazz = null;
        try {
            dtoClazz = (Class<T>) Class.forName(DTO_IN_PREFIX+ MyStringUtils.toUpFirstCharacter(transCode)+DTO_IN_SUFFIX);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        T t= JSON.parseObject(jsonText,dtoClazz);
        return strategy.execute(t);
    }
}
