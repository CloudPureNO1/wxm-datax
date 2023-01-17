package com.wxm.security.service;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/4/28 10:09
 * @since 1.0.0
 */
public interface ITestService {
    @PreAuthorize("hasAuthority('MTMS_ROLE_TEST')")
    void test();
}
