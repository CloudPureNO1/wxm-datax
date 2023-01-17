package com.wxm.dao.test;

import com.wxm.base.dto.DataRtn;
import com.wxm.dao.mapper.ds1.WxmDictionaryMapper;
import com.wxm.dao.model.ds1.WxmDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/2 13:47
 * @since 1.0.0
 */
@RestController
public class TestController {
    @Autowired
    private WxmDictionaryMapper dimDictionaryMapper;
    @GetMapping("/test")
    public DataRtn test(){
        WxmDictionary entity=new WxmDictionary();
        entity.setDictLabel("test");
        entity.setDictParentValue("root");
        entity.setDictStatus("0");
        entity.setDictType("1");
        entity.setDictValue("t1001");
        dimDictionaryMapper.insert(entity);
        dimDictionaryMapper.selectAll();
        return new DataRtn().success().message("ok");
    }
}
