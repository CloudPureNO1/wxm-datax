package com.wxm.util.my.desensitization;

/**
 * 混合型
 */

public enum CustomMultiEnum {

    //身份证号
    ID_CARD_15(DesensitizationTypeEnum.ID_CARD,15),
    ID_CARD(DesensitizationTypeEnum.ID_CARD,18),
    //手机号
    MOBILE_PHONE(DesensitizationTypeEnum.MOBILE_PHONE,11),
    // 银行卡号
    BANK_CARD(DesensitizationTypeEnum.BANK_CARD);


    public DesensitizationTypeEnum getType() {
        return type;
    }

    public void setType(DesensitizationTypeEnum type) {
        this.type = type;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    private DesensitizationTypeEnum type;
    private int len;

    CustomMultiEnum(DesensitizationTypeEnum type, int len) {
        this.type = type;
        this.len = len;
    }
    CustomMultiEnum(DesensitizationTypeEnum type) {
        this.type = type;
    }

    public DesensitizationTypeEnum getType(int len){
        for (CustomMultiEnum customMultiEnum : CustomMultiEnum.values()) {
            if(customMultiEnum.getLen() == len){
                return customMultiEnum.getType();
            }
        }
        return null;
    }
}
