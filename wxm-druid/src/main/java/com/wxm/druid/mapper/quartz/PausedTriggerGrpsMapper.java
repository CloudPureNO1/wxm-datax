package com.wxm.druid.mapper.quartz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.quartz.PausedTriggerGrps;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * qrtz_paused_trigger_grps表 用来存储已暂停的Trigger组的信息。 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@DS("quartz")
@Mapper
public interface PausedTriggerGrpsMapper extends BaseMapper<PausedTriggerGrps> {

}
