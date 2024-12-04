package com.wxm.api;

import com.alibaba.fastjson.JSON;
import com.wxm.base.dto.quartz.out.Quartz11001Out;
import com.wxm.druid.entity.quartz.nodb.JobDetailAndTriggerBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
// 在使用springboot单元测试出现：JAVA: Java.lang.IllegalStateException javax.websocket.server.ServerContainer not availableJ
// 在springboot测试类上加webEnvironment
// 使用@SpringBootTest后，Spring将加载所有被管理的bean，基本等同于启动了整个服务，此时便可以开始功能测试。
@SpringBootTest(classes = {WxmApiApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class WxmApiApplicationTests {

    @Test
    void contextLoads() {
        List<Quartz11001Out> listTarget = new ArrayList<>();
        List<JobDetailAndTriggerBean> listSource = new ArrayList<>();
        JobDetailAndTriggerBean bean = new JobDetailAndTriggerBean();
        bean.setJobName("test").setJobClassName("Test.class").setJobGroup("GTest").setRepeatInterval(10L).setTriggerName("testT");
        listSource.add(bean);
        BeanUtils.copyProperties(listSource, listTarget);
        log.info(">>>>>>>>>>>>>>>>结果：{}", JSON.toJSONString(listTarget));

    }



}
