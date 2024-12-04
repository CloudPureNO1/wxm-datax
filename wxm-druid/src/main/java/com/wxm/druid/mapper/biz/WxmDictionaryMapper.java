package com.wxm.druid.mapper.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.biz.WxmDictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
@Mapper
// @DS("ds1")  ds1 默认为主数据源，可以不配置
@DS("biz")
@Repository
public interface WxmDictionaryMapper extends BaseMapper<WxmDictionary> {
    List<WxmDictionary> selectAll();

    /**
     * 条件查询
     * @param dictId
     * @param dictType
     * @param dictStatus
     * @param dictLabel
     * @return
     */
    List<WxmDictionary> selectByCon(@Param("dictId")Long dictId,@Param("dictType")String dictType,@Param("dictStatus")String dictStatus,@Param("dictLabel")String dictLabel);
}
