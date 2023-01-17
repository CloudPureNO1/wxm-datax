package com.wxm.dao.mapper.ds2;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.dao.model.ds2.Locks;

/**
 * <p>
 * qrtz_locks表 用来存储程序的悲观锁的信息(假如使用了悲观锁)。 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
public interface LocksMapper extends BaseMapper<Locks> {

}
