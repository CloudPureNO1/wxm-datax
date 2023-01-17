package com.wxm.service.biz;

import com.wxm.base.dto.quartz.in.*;
import com.wxm.base.dto.quartz.out.*;
import com.wxm.base.exception.*;
import com.wxm.druid.entity.quartz.nodb.JobDetailAndTriggerBean;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/4/29 14:56
 * @since 1.0.0
 */
@Validated
public interface IBizQuartzService{

    /**
     * 查询定时任务和触发器基本信息
     * @param quartz11001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     * @throws UtilException
     */
    List<Quartz11001Out>service11001(@Valid Quartz11001In quartz11001In) throws BizSvcException, DbSvcException, UtilException;


    /**
     * 查询触发器列表
     * @param quartz11002In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     * @throws UtilException
     */
    Quartz11002Out service11002(@Valid Quartz11002In quartz11002In) throws BizSvcException, DbSvcException, UtilException;


    /**
     * 查询触发器列表
     * @param quartz11003In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     * @throws UtilException
     */
    Quartz11003Out service11003(@Valid Quartz11003In quartz11003In) throws BizSvcException, DbSvcException, UtilException;

    /**
     * 根据定时任务名称查询datax的数据同步信息
     * @param quartz11004In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     * @throws UtilException
     */
    Quartz11004Out service11004(@Valid Quartz11004In quartz11004In) throws BizSvcException, DbSvcException, UtilException;

    /**
     * 查询定时任务和触发器基本信息
     * @return
     * @throws DbSvcException  操作数据库的service层异常
     * @throws BizSvcException 业务层异常
     */
    List<JobDetailAndTriggerBean> getJobDetailAndTriggerList() throws DbSvcException, BizSvcException;


    /**
     * 新增quart+datax 数据同步的定时任务
     * @param quartz12001In
     * @return
     * @throws BizSvcException
     * @throws JobException
     */
    Quartz12001Out service12001(@Valid Quartz12001In quartz12001In) throws BizSvcException, JobException;


    /**
     * 新增普通定时任务
     * @param quartz12002In
     * @return
     * @throws BizSvcException
     * @throws JobException
     */
    Quartz12002Out service12002(@Valid Quartz12002In quartz12002In) throws BizSvcException, JobException;

    /**
     * 修改 quart+datax 数据同步的定时任务
     * @param quartz13001In
     * @return
     * @throws BizSvcException
     * @throws JobException
     */
    Quartz13001Out service13001(@Valid Quartz13001In quartz13001In) throws BizSvcException, JobException;
    /**
     * 修改 普通定时任务
     * @param quartz13002In
     * @return
     * @throws BizSvcException
     * @throws JobException
     */
    Quartz13002Out service13002(@Valid Quartz13002In quartz13002In) throws BizSvcException, JobException;

    /**
     * 删除quart+datax 数据同步的定时任务
     * @param quartz14001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     * @throws UtilException
     */
    Quartz14001Out service14001(@Valid Quartz14001In quartz14001In) throws BizSvcException, JobException;

    /**
     * 删除普通定时任务
     * @param quartz14002In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     * @throws UtilException
     */
    Quartz14002Out service14002(@Valid Quartz14002In quartz14002In) throws BizSvcException, JobException;

    /**
     * Quartz定时任务停止
     * @param quartz15001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Quartz15001Out service15001(@Valid Quartz15001In quartz15001In) throws BizSvcException, JobException;

    /**
     * Quartz定时任务恢复
     * @param quartz16001In
     * @return
     * @throws BizSvcException
     * @throws JobException
     */
    Quartz16001Out service16001(@Valid Quartz16001In quartz16001In) throws BizSvcException, JobException;
}
