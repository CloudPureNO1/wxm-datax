package com.wxm.base.validator;

import com.wxm.base.annotation.validate.CollectionValidate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * <p></p>
 * <p>
 * 必须实现ConstraintValidator<A, T>接口，A是自定义效验注解，T是支持效验的数据类型。
 * initialize()：初始化验证器相关内容。
 * isValid()：实际验证方法。
 * </p>
 *
 * @author 王森明
 * @date 2022/7/20 14:07
 * @since 1.0.0
 */
public class CollectionValidator implements ConstraintValidator<CollectionValidate, Collection<?>> {

    @Override
    public void initialize(CollectionValidate constraintAnnotation) {

    }

    @Override
    public boolean isValid(Collection<?> value, ConstraintValidatorContext context) {
        if(CollectionUtils.isEmpty(value)){return false;}
        return !value.stream().anyMatch(item-> ObjectUtils.isEmpty(item));
    }
}
