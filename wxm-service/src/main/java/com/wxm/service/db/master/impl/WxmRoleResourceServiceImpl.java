package com.wxm.service.db.master.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxm.base.enums.DbSvcEnum;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.master.WxmRoleResource;
import com.wxm.druid.mapper.master.WxmRoleResourceMapper;
import com.wxm.service.db.master.IWxmRoleResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色-资源关联表 服务实现类
 * </p>
 *
 * @author 王森明
 * @since 2022-06-17
 */
@Slf4j
@Service
public class WxmRoleResourceServiceImpl extends ServiceImpl<WxmRoleResourceMapper, WxmRoleResource> implements IWxmRoleResourceService {

    @Override
    public int deleteByRoleIds(List<Long> list) throws DbSvcException {
        try{
            return baseMapper.deleteByRoleIds(list);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1004_B.toString(),DbSvcEnum.DB_SVC_1004_B.getMessage("根据角色Id列表删除"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1004_B.toString(),DbSvcEnum.DB_SVC_1004_B.getMessage("根据角色Id列表删除"),e.getMessage());
        }
    }
}
