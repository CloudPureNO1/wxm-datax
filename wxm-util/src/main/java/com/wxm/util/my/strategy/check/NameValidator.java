package com.wxm.util.my.strategy.check;


import com.wxm.base.enums.DesensitizationEnum;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-12 16:52:24
 */
@Component
public class NameValidator implements DesensitizationValidator {
    @Override
    public DesensitizationEnum getEnum() {
        return DesensitizationEnum.NAME;
    }

    @Override
    public boolean validate(String value) {
        return Validator.isChineseName(value);
    }
}
