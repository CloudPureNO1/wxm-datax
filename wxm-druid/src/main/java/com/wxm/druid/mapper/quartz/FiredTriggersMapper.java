package com.wxm.druid.mapper.quartz;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.quartz.FiredTriggers;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * qrtz_fired_triggers表 用来存储已触发的Trigger相关的状态信息，以及相关联Job的执行信息。 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@Repository
@DS("quartz")
@Mapper
public interface FiredTriggersMapper extends BaseMapper<FiredTriggers> {

}
