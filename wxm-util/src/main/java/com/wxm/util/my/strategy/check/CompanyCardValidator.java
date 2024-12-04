package com.wxm.util.my.strategy.check;

import com.wxm.base.enums.DesensitizationEnum;
import org.springframework.stereotype.Component;

/**
 * 统一社会信用代码
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-12 17:07:35
 */
@Component
public class CompanyCardValidator implements DesensitizationValidator{
    @Override
    public DesensitizationEnum getEnum() {
        return DesensitizationEnum.COMPANY_CARD;
    }

    @Override
    public boolean validate(String value) {
        return Validator.isCreditCode(value);
    }
}
