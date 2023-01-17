package com.wxm.druid.mapper.quartz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.quartz.SimpropTriggers;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * qrtz_simprop_triggers表 用来存储存储CalendarIntervalTrigger和DailyTimeIntervalTrigger。 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@DS("quartz")
@Mapper
public interface SimpropTriggersMapper extends BaseMapper<SimpropTriggers> {

}
