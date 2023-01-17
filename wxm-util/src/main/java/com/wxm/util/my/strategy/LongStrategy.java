package com.wxm.util.my.strategy;

import org.springframework.util.StringUtils;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/10 13:48
 * @since 1.0.0
 */
public class LongStrategy implements Strategy<Long>{


    @Override
    public Long parseToObject(String value) {
        if(null==value||!StringUtils.hasLength(value)){
            return null;
        }
        return Long.valueOf(value);
    }

    @Override
    public String transToString(Long value) {
        try {
            return String.valueOf(value);
        } catch (Exception e) {
            e.printStackTrace();
            return value.toString();
        }
    }
}
