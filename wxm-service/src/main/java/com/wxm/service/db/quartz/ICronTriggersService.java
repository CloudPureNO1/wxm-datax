package com.wxm.service.db.quartz;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxm.druid.entity.quartz.CronTriggers;

/**
 * <p>
 * qrtz_cron_triggers表 用来存储触发器 Cron表达式和时区信息。 服务类
 * </p>
 *
 * @author 王森名
 * @since 2022-07-06
 */
public interface ICronTriggersService extends IService<CronTriggers> {

}
