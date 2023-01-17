package com.wxm.dao;

import com.alibaba.fastjson.JSON;
import com.wxm.base.exception.DbSvcException;
import com.wxm.dao.mapper.ds1.WxmDictionaryMapper;
import com.wxm.dao.model.ds1.WxmDictionary;
import com.wxm.dao.model.ds2.nodb.JobDetailAndTriggerBean;
import com.wxm.dao.service.db.quartz.IJobDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WxmDaoApplication.class)
class WxmDaoApplicationTests {
    @Autowired
    private IJobDetailsService jobDetailsService;
    @Autowired
    private WxmDictionaryMapper dictionaryMapper;
    @Test
    void contextLoads() throws DbSvcException {
/*        WxmDictionary entity=new WxmDictionary();
        entity.setDictLabel("test");
        entity.setDictParentValue("root");
        entity.setDictStatus("0");
        entity.setDictType("1");
        entity.setDictValue("t1001");
        dictionaryMapper.insert(entity);*/


       List<JobDetailAndTriggerBean> listJobDetails = jobDetailsService.queryJobDetailAndTriggerList();
        List<WxmDictionary> listDicAll = dictionaryMapper.selectAll();
        log.info("jobDetals is {}", JSON.toJSONString(listJobDetails));
        log.info("dictionary is {}", JSON.toJSONString(listDicAll));

    }

}
