package com.wxm.druid;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxm.druid.entity.quartz.DataXDetails;
import com.wxm.druid.mapper.quartz.DataXDetailsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-02-26 9:40:20
 */
@Slf4j
@Component
public class TestDb {
//    @Autowired
//    private WxmUserMapper wxmUserMapper;
    @Autowired
    private DataXDetailsMapper dataXDetailsMapper;

//    @DS("quartz")
    public String test(){
//        List<WxmUser> wxmUsers = wxmUserMapper.selectAll();
//       log.info(">>>>",wxmUsers);


        List<DataXDetails> dataXDetails = dataXDetailsMapper.selectList(new QueryWrapper<>());
        log.info(">>>>",dataXDetails);

        return "";
    }
}
