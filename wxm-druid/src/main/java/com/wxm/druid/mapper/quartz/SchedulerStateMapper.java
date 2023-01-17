package com.wxm.druid.mapper.quartz;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.quartz.SchedulerState;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@DS("quartz")
@Mapper
public interface SchedulerStateMapper extends BaseMapper<SchedulerState> {

}
