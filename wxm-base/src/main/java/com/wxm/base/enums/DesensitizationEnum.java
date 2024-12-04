package com.wxm.base.enums;

import org.springframework.util.StringUtils;



public enum DesensitizationEnum  {
    NAME("name","姓名脱敏","(.{1})(.{1})(.*)", "$1*$3"),
    ID_CARD("idCard","身份证号脱敏","(\\d{6})\\d{8}(\\w{4})","$1********$2"),
    MOBILE("mobile","手机号脱敏","(\\d{3})\\d{4}(\\w{4})","$1****$2"),
    ADDRESS("address","地址脱敏","", ""),
    COMPANY_CARD("companyCard","统一社会信用代码脱敏","(.{8})(.{9})(.{1})","$1*********$3"),
    COMPANY("companyName","公司名称脱敏","",""),
    SB("sb","社保账号脱敏","(\\d{6})\\d{8}(\\w{4})","$1********$2"),
    EMAIL("email","邮箱脱敏","(^\\w)[^@]*(@.*$)","$1****$2"),
    ;
    String codeValue;
    String codeText;
    String ext1;
    String ext2;
    DesensitizationEnum(String codeValue, String codeText, String ext1, String ext2) {
        this.codeValue = codeValue;
        this.codeText = codeText;
        this.ext1=ext1;
        this.ext2=ext2;
    }
    /**
     * Returns the name of this enum constant, as contained in the
     * declaration.  This method may be overridden, though it typically
     * isn't necessary or desirable.  An enum type should override this
     * method when a more "programmer-friendly" string form exists.
     *
     * @return the name of this enum constant
     */
    @Override
    public String toString() {
        return codeValue;
    }

    public String getCodeValue(){
        return codeValue;
    }
}
