package com.wxm.druid.mapper.biz;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.biz.WxmOperateLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 操作日志 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2024-06-13
 */
@DS("biz")
@Mapper
@Repository
public interface WxmOperateLogMapper extends BaseMapper<WxmOperateLog> {

}
