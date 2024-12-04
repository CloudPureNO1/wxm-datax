package com.wxm.druid.mapper.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wxm.druid.entity.biz.RsApi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * api表 Mapper 接口
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@DS("biz")
@Mapper
public interface RsApiMapper extends BaseMapper<RsApi> {

}
