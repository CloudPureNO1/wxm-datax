package com.wxm.security.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.Filter;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/8/5 17:02
 * @since 1.0.0
 */
@Order(-98)
public class XssFilter extends SecurityFilter implements Filter {

}
