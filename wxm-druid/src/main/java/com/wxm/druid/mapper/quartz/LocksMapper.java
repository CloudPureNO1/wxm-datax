package com.wxm.druid.mapper.quartz;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.quartz.Locks;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * qrtz_locks表 用来存储程序的悲观锁的信息(假如使用了悲观锁)。 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@DS("quartz")
@Mapper
public interface LocksMapper extends BaseMapper<Locks> {

}
