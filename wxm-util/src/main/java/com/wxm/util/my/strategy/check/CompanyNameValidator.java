package com.wxm.util.my.strategy.check;

import com.wxm.base.enums.DesensitizationEnum;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-12 17:08:02
 */
@Component
public class CompanyNameValidator implements DesensitizationValidator{
    @Override
    public DesensitizationEnum getEnum() {
        return DesensitizationEnum.COMPANY;
    }

    @Override
    public boolean validate(String value) {
        return isCompanyName(value);
    }

    public boolean isCompanyName(CharSequence sequence) {
        // 正则表达式匹配英文名称
        String regexEnglish = "^[a-zA-Z\\s-]+(,?[a-zA-Z\\s-]+)*$";
        // 正则表达式匹配中文名称
        String regexChinese = "^[\u4e00-\u9fa5]+$";

        boolean isEnglishValid = sequence.toString().matches(regexEnglish);
        boolean isChineseValid = sequence.toString().matches(regexChinese);

        return isEnglishValid || isChineseValid;
    }



}
