package com.wxm.druid.mapper.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.biz.WxmApiLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 接口日志 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2024-06-13
 */
@DS("biz")
@Mapper
public interface WxmApiLogMapper extends BaseMapper<WxmApiLog> {

}
