package com.wxm.service.db.quartz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxm.base.dto.quartz.in.Quartz11002In;
import com.wxm.base.dto.quartz.in.Quartz11003In;
import com.wxm.base.enums.DbSvcEnum;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.quartz.Triggers;
import com.wxm.druid.entity.quartz.nodb.TriggerSelectIn;
import com.wxm.druid.mapper.quartz.TriggersMapper;
import com.wxm.service.db.quartz.ITriggerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/7/4 15:00
 * @since 1.0.0
 */
@Slf4j
@Service
public class TriggerService   extends ServiceImpl<TriggersMapper, Triggers>  implements ITriggerService {
    private final TriggersMapper triggerMapper;

    public TriggerService(TriggersMapper triggerMapper) {
        this.triggerMapper = triggerMapper;
    }

    @Override
    public PageInfo<Triggers> queryByCon(Quartz11002In quartz11002In) throws DbSvcException {
        try{
            PageHelper.startPage(quartz11002In.getCurrentPage(),quartz11002In.getPageSize());
            // 全部都是String类型，给String 类型复制，类型字段都一样，采用spring的
            TriggerSelectIn triggerSelectIn=new TriggerSelectIn();
            org.springframework.beans.BeanUtils.copyProperties(quartz11002In,triggerSelectIn);
           List<Triggers>list=triggerMapper.selectByCon(triggerSelectIn);

            return new PageInfo<>(list);
        }catch (Exception e){
            log.error("【{}】{}：{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("定时任务触发器列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("定时任务触发器列表"));
        }
    }

    @Override
    public PageInfo<Map<String,String>> queryByConFmt(Quartz11003In quartz11003In) throws DbSvcException {
        try{
            PageHelper.startPage(quartz11003In.getCurrentPage(),quartz11003In.getPageSize());
            // 全部都是String类型，给String 类型复制，类型字段都一样，采用spring的
            TriggerSelectIn triggerSelectIn=new TriggerSelectIn();
            org.springframework.beans.BeanUtils.copyProperties(quartz11003In,triggerSelectIn);
            List<Map<String,String>>list=triggerMapper.selectByConFmt(triggerSelectIn);
            return new PageInfo<>(list);
        }catch (Exception e){
            log.error("【{}】{}：{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("定时任务触发器列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("定时任务触发器列表"));
        }
    }
}
