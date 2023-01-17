package com.wxm.dao.mapper.ds2;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.dao.model.ds2.FiredTriggers;

/**
 * <p>
 * qrtz_fired_triggers表 用来存储已触发的Trigger相关的状态信息，以及相关联Job的执行信息。 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
public interface FiredTriggersMapper extends BaseMapper<FiredTriggers> {

}
