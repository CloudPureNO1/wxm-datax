package com.wxm.security.service.impl;

import com.wxm.security.service.ITestService;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/4/28 10:10
 * @since 1.0.0
 */
@Service
public class TestServiceImpl implements ITestService {

    @Override
    public void test() {
        System.out.println(">>>>>>>>>>>>>>>>测试service");
    }
}
