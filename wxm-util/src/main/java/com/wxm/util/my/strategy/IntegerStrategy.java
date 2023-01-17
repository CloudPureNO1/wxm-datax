package com.wxm.util.my.strategy;

import org.springframework.util.StringUtils;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/10 13:54
 * @since 1.0.0
 */
public class IntegerStrategy implements Strategy<Integer>{

    @Override
    public Integer parseToObject(String value) {
        if(null==value||!StringUtils.hasLength(value)){
            return null;
        }
        return Integer.valueOf(value);
    }

    @Override
    public String transToString(Integer value) {
        try {
            return String.valueOf(value);
        } catch (Exception e) {
            return value.toString();
        }
    }
}
