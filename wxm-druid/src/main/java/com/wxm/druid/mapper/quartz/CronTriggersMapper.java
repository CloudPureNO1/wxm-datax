package com.wxm.druid.mapper.quartz;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.quartz.CronTriggers;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * qrtz_cron_triggers表 用来存储触发器 Cron表达式和时区信息。 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@Repository
@DS("quartz")
@Mapper
public interface CronTriggersMapper extends BaseMapper<CronTriggers> {

}
