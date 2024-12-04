package com.wxm.util.my.excel;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-03-14 17:19:51
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {
    /**
     * Excel标题
     * @return
     */
    String value() default "";

    /**
     * Excel从左往右排列位置
     * @return
     */
    int col() default 0;


    /**
     * 字典key
     **/
    String dicKey() default "";

    /**
     * 列宽
     * @return
     */
    int width() default 0;

}
