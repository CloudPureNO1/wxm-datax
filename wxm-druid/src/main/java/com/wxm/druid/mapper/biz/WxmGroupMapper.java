package com.wxm.druid.mapper.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.biz.WxmGroup;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * <p>
 * 用户组 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@DS("biz")
@Mapper
@Repository
public interface WxmGroupMapper extends BaseMapper<WxmGroup> {
    List<WxmGroup> selectAll();
    List<WxmGroup>selectPageByCon(WxmGroup entity);
}
