package com.wxm.util.my.strategy;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/10 14:02
 * @since 1.0.0
 */
public class BigDecimalStrategy implements Strategy<BigDecimal> {

    @Override
    public BigDecimal parseToObject(String value) {
        if(null==value||!StringUtils.hasLength(value)){
            return null;
        }
        return new BigDecimal(value);
    }

    @Override
    public String transToString(BigDecimal value) {
        return value.toPlainString();
    }
}
