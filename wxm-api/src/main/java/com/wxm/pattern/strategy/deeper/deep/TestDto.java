package com.wxm.pattern.strategy.deeper.deep;

import com.wxm.util.my.desensitization.CustomMultiEnum;
import com.wxm.util.my.desensitization.Desensitization;
import com.wxm.util.my.desensitization.DesensitizationTypeEnum;
import lombok.Data;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-05-14 10:27:47
 */
@Data
public class TestDto {
    @Desensitization(type = DesensitizationTypeEnum.CUSTOM,startExclude =-1,
            typeArr = {CustomMultiEnum.MOBILE_PHONE,CustomMultiEnum.BANK_CARD,CustomMultiEnum.ID_CARD,CustomMultiEnum.ID_CARD_15})
    private String loginname;
    @Desensitization(type = DesensitizationTypeEnum.ID_CARD)
    private String idCard;

    @Desensitization(type=DesensitizationTypeEnum.PASSWORD)
    private String password;
    @Desensitization(type=DesensitizationTypeEnum.MOBILE_PHONE)
    private String phone;
    @Desensitization(type=DesensitizationTypeEnum.FIXED_PHONE)
    private String tel;
}
