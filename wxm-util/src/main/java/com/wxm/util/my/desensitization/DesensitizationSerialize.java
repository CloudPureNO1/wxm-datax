package com.wxm.util.my.desensitization;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.DesensitizedUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 自定义脱敏序列化类
 */
@AllArgsConstructor
@NoArgsConstructor
public class DesensitizationSerialize extends JsonSerializer<String> implements ContextualSerializer {

    private DesensitizationTypeEnum type;

    private int startIndex;

    private int endIndex;
    private CustomMultiEnum [] typeArr;

    @Override
    public void serialize(String str, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
         excute(str,jsonGenerator,serializerProvider,type);
    }

    public void  excute(String str, JsonGenerator jsonGenerator, SerializerProvider serializerProvider,DesensitizationTypeEnum typeEnum) throws IOException {
        switch (typeEnum) {
            // 自定义类型脱敏
            case CUSTOM:
                /**
                 * 例如：用户名可以存在多种模式：有些需要脱敏，有写不需要脱敏
                 * 1、符合规则的普通字符串
                 * 2、手机号
                 * 3、身份证号码
                 * 4、银行卡号
                 * ...
                 *
                 */
                if(typeArr.length!=0){
                    // 需要脱敏的符合银行卡规则的字符串
                    List<CustomMultiEnum> collect = Arrays.stream(typeArr).filter(multiEnum -> multiEnum.getType() == DesensitizationTypeEnum.BANK_CARD).collect(Collectors.toList());
                    if(!CollectionUtils.isEmpty(collect) && checkBankCard(str)){
                        jsonGenerator.writeString(DesensitizedUtil.bankCard(String.valueOf(str)));
                        break;
                    }
                    // 脱敏其他需要脱敏的字符串，例如：15/18为的身份证，11为手机号
                    collect = Arrays.stream(typeArr).filter(multiEnum -> (multiEnum.getType(str.length())!=null)).collect(Collectors.toList());
                    if(!CollectionUtils.isEmpty(collect)){
                        excute(str,jsonGenerator,serializerProvider,collect.get(0).getType(str.length()));
                        break;
                    }
                    // 不需要脱敏
                    jsonGenerator.writeString(str);
                    break;
                }
                // 脱敏开始位置小于0，标识不需要脱敏，优先级低于typeArr
                if(startIndex<0){
                    jsonGenerator.writeString(str);
                    break;
                }

                // 默认按照 脱敏开始和结束位置进行脱模
                jsonGenerator.writeString(CharSequenceUtil.hide(String.valueOf(str), startIndex,
                        endIndex >= startIndex ? endIndex : str.length() + endIndex));
                break;
            // userId脱敏
            case USER_ID:
                jsonGenerator.writeString(String.valueOf(DesensitizedUtil.userId()));
                break;
            // 中文姓名脱敏
            case CHINESE_NAME:
                jsonGenerator.writeString(CharSequenceUtil.hide(String.valueOf(str), 1, str.length()));
                break;
            // 身份证脱敏
            case ID_CARD:
                jsonGenerator.writeString(DesensitizedUtil.idCardNum(String.valueOf(str), 1, 2));
                break;
            // 固定电话脱敏
            case FIXED_PHONE:
                jsonGenerator.writeString(DesensitizedUtil.fixedPhone(String.valueOf(str)));
                break;
            // 手机号脱敏
            case MOBILE_PHONE:
                jsonGenerator.writeString(DesensitizedUtil.mobilePhone(String.valueOf(str)));
                break;
            // 地址脱敏
            case ADDRESS:
                jsonGenerator.writeString(DesensitizedUtil.address(String.valueOf(str), 8));
                break;
            // 邮箱脱敏
            case EMAIL:
                jsonGenerator.writeString(DesensitizedUtil.email(String.valueOf(str)));
                break;
            // 密码脱敏
            case PASSWORD:
                jsonGenerator.writeString(DesensitizedUtil.password(String.valueOf(str)));
                break;
            // 中国车牌脱敏
            case CAR_LICENSE:
                jsonGenerator.writeString(DesensitizedUtil.carLicense(String.valueOf(str)));
                break;
            // 银行卡脱敏
            case BANK_CARD:
                jsonGenerator.writeString(DesensitizedUtil.bankCard(String.valueOf(str)));
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            // 判断数据类型是否为String类型
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
                // 获取定义的注解
                Desensitization desensitization = beanProperty.getAnnotation(Desensitization.class);
                if (desensitization == null) {
                    desensitization = beanProperty.getContextAnnotation(Desensitization.class);
                }
                if (desensitization != null) {
                    return new DesensitizationSerialize(desensitization.type(),
                            desensitization.startExclude(),
                            desensitization.endExclude(),
                            desensitization.typeArr());
                }
            }

            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(null);
    }

    public  boolean checkBankCard(String bankCard) {
        if (bankCard == null || bankCard.isEmpty() || bankCard.length() < 13 || bankCard.length() > 19) {
            // 银行卡号长度应该在13到19位之间
            return false;
        }

        char[] cardNoArr = bankCard.toCharArray();
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNoArr.length - 1; i >= 0; i--) {
            int n = cardNoArr[i] - '0';
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }

        // 如果总和能被10整除，则说明校验位正确，银行卡号有效
        return (sum % 10) == 0;
    }
}