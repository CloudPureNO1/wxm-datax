package com.wxm.util.my.strategy;

import org.springframework.util.StringUtils;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/10 13:52
 * @since 1.0.0
 */
public class ShortStrategy implements Strategy<Short>{

    @Override
    public Short parseToObject(String value) {
        if(null==value||!StringUtils.hasLength(value)){
            return null;
        }
        return Short.valueOf(value);
    }

    @Override
    public String transToString(Short value) {
        try {
            return String.valueOf(value);
        } catch (Exception e) {
            return value.toString();
        }
    }
}
