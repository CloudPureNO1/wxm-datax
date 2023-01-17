package com.wxm.druid.mapper.quartz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.quartz.SimpleTriggers;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * qrtz_simple_triggers表 用来存储简单的 Trigger，包括重复次数，间隔，以及已触发的次数 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@DS("quartz")
@Mapper
public interface SimpleTriggersMapper extends BaseMapper<SimpleTriggers> {

}
