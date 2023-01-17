package com.wxm.dao.mapper.ds1;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.dao.model.ds1.WxmDictionary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@Repository
public interface WxmDictionaryMapper extends BaseMapper<WxmDictionary> {
    List<WxmDictionary> selectAll();
}
