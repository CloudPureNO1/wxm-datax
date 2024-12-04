package com.wxm.util.my.strategy.check;


import com.wxm.base.enums.DesensitizationEnum;

/**
 * 校验
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-12 16:51:22
 */
public interface DesensitizationValidator {
    /**
     * 获取注解类型
     * @return
     */
    DesensitizationEnum getEnum();

    /**
     * 脱敏处理
     *
     * @param value
     * @return
     */
    boolean validate(String value);
}
