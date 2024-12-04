package com.wxm.util.my.desensitization;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 脱敏注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = DesensitizationSerialize.class)
public @interface Desensitization {
    /**
     * 脱敏数据类型，CUSTOM注解下，startInclude和endExclude生效
     */
    DesensitizationTypeEnum type() default DesensitizationTypeEnum.CUSTOM;

    /**
     * 脱敏开始位置（包含）  ,小于0 时，不脱敏
     */
    int startExclude() default 0;

    /**
     * 脱敏结束位置（不包含）
     */
    int endExclude() default 0;




    /**
     * 混合型字段，比如：用户名可以使符合规则的输入数据、可以使手机、可以是身份证号
     * 前置条件： type= DesensitizationTypeEnum.CUSTOM
     */
    CustomMultiEnum[] typeArr() default {};
}