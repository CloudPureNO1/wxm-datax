package com.wxm.util.my.strategy.check;

import com.wxm.base.enums.DesensitizationEnum;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-12 17:09:02
 */
@Component
public class EmailValidator implements DesensitizationValidator {


    @Override
    public DesensitizationEnum getEnum() {
        return DesensitizationEnum.EMAIL;
    }

    @Override
    public boolean validate(String value) {
        return Validator.isEmail(value);
    }


}
