package com.wxm.quartz;

import com.wxm.druid.TestDb;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WxmQuartzApplication.class})
class WxmQuartzApplicationTests {

    @Autowired
    private TestDb testDb;
    @Test
    void contextLoads() {
        testDb.test();
    }

}
