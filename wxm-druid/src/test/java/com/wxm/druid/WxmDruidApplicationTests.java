package com.wxm.druid;

import com.wxm.base.exception.BizSvcException;
import com.wxm.base.exception.DbSvcException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WxmDruidApplication.class})
class WxmDruidApplicationTests {
    @Autowired
    private TestDb testDb;
    @Test
    void contextLoads() throws BizSvcException, DbSvcException {

        testDb.test();
        Assert.assertEquals(1,1);
    }

}
