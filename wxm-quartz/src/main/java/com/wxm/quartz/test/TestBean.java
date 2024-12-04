package com.wxm.quartz.test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/4/28 16:40
 * @since 1.0.0
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
public class TestBean {
    private String name;
    private String age;
}
