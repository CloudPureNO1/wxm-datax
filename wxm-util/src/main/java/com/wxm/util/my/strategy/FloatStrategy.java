package com.wxm.util.my.strategy;

import org.springframework.util.StringUtils;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/10 13:55
 * @since 1.0.0
 */
public class FloatStrategy implements Strategy<Float>{

    @Override
    public Float parseToObject(String value) {
        if(null==value||!StringUtils.hasLength(value)){
            return null;
        }
        return Float.valueOf(value);
    }

    @Override
    public String transToString(Float value) {
        try {
            return String.valueOf(value);
        } catch (Exception e) {
            return value.toString();
        }
    }
}
