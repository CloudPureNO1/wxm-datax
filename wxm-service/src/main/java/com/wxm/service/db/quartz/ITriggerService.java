package com.wxm.service.db.quartz;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.wxm.base.dto.quartz.in.Quartz11002In;
import com.wxm.base.dto.quartz.in.Quartz11003In;
import com.wxm.base.dto.quartz.item.Item11003Out;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.quartz.Triggers;
import com.wxm.service.db.IDbService;

import java.util.Map;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/7/4 14:56
 * @since 1.0.0
 */
public interface ITriggerService  extends IDbService, IService<Triggers> {

    /**
     * 获取触发器的列表
     * @param quartz11002In
     * @return
     * @throws DbSvcException
     */
    PageInfo<Triggers> queryByCon(Quartz11002In quartz11002In) throws DbSvcException;
    /**
     * 获取触发器的列表
     * @param quartz11003In
     * @return
     * @throws DbSvcException
     */
    PageInfo<Map<String,String>> queryByConFmt(Quartz11003In quartz11003In) throws DbSvcException;
}
