package com.wxm.dao.mapper.ds2;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.dao.model.ds2.SimpleTriggers;

/**
 * <p>
 * qrtz_simple_triggers表 用来存储简单的 Trigger，包括重复次数，间隔，以及已触发的次数 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
public interface SimpleTriggersMapper extends BaseMapper<SimpleTriggers> {

}
