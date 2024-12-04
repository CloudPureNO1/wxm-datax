package com.wxm.core.annotation;

import com.wxm.base.enums.EncryptTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>接口日志注解</p>
 * <p>
 * 有时候框架提供的注解不满足于实际开发需求时，需要对其进行扩展。
 * @Target ：指定注解可以用于：字段、注解、参数。
 * @Retention ：指定运行期间有效。
 * @Constraint ：指定验证器。
 * groups()和payload()是自定义效验注解必须的内容。
 * </p>
 *
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-06-13 16:36:12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiLog {
    /**
     * 参数加密类型  EncodeTypeEnum
     * @return
     */

    EncryptTypeEnum encryptType() default EncryptTypeEnum.PLAIN;

    /**
     * 用于标志 入参中哪那些字段  是 加密 的数据   （其他对象模式的：比如实体类、Map,JSONObject 不能设置，字符串也不能设置）
     * @return
     */
    String [] encryptTypeObj() default {};
}
