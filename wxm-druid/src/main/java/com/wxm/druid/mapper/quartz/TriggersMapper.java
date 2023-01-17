package com.wxm.druid.mapper.quartz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.quartz.Triggers;
import com.wxm.druid.entity.quartz.nodb.TriggerSelectIn;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * <p>
 * 触发器表 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@Repository
@DS("quartz")
@Mapper
public interface TriggersMapper extends BaseMapper<Triggers> {
    /**
     * 条件查询触发器
     * @param triggerSelectIn
     * @return
     */
    List<Triggers>selectByCon(TriggerSelectIn triggerSelectIn);

    /**
     * 条件查询触发器，Map<String,String></String,String>
     * @param triggerSelectIn
     * @return
     */
    List<Map<String,String>>selectByConFmt(TriggerSelectIn triggerSelectIn);

}
