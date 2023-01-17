package com.wxm.base.annotation.validate;


import com.wxm.base.validator.CollectionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * <p></p>
 * <p>
 * 有时候框架提供的注解不满足于实际开发需求时，需要对其进行扩展。
 * @Target ：指定注解可以用于：字段、注解、参数。
 * @Retention ：指定运行期间有效。
 * @Constraint ：指定验证器。
 * groups()和payload()是自定义效验注解必须的内容。
 * </p>
 *
 * @author 王森明
 * @date 2022/7/20 14:03
 * @since 1.0.0
 */
@Documented
@Target({ElementType.FIELD,ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CollectionValidator.class)
public @interface CollectionValidate {
    String message() default "";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
