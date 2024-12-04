package com.wxm.service.db.biz.impl;

import com.wxm.base.enums.DbSvcEnum;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.biz.WxmRoleApi;
import com.wxm.druid.mapper.biz.WxmRoleApiMapper;
import com.wxm.service.db.biz.IWxmRoleApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色api 关联表 服务实现类
 * </p>
 *
 * @author 王森明
 * @since 2022-06-27
 */
@Slf4j
@Service("wxmRoleApiService")
public class WxmRoleApiService extends ServiceImpl<WxmRoleApiMapper, WxmRoleApi> implements IWxmRoleApiService {

    @Override
    public int deleteBatchByRoleIdAndApiIds(Long roleId, List<Long> list) throws DbSvcException {
        try{
            return baseMapper.deleteBatchByRoleIdAndApiIds(roleId,list);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1004_A.toString(),DbSvcEnum.DB_SVC_1004_A.getMessage("根据角色Id和接口Id列表删除"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1004_A.toString(),DbSvcEnum.DB_SVC_1004_A.getMessage("根据角色Id和接口Id列表删除"),e.getMessage());
        }
    }

    @Override
    public int deleteByApiIds(List<Long> list) throws DbSvcException {
        try{
            return baseMapper.deleteByApiIds(list);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1004_A.toString(),DbSvcEnum.DB_SVC_1004_A.getMessage("根据接口Id列表删除"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1004_A.toString(),DbSvcEnum.DB_SVC_1004_A.getMessage("根据接口Id列表删除"),e.getMessage());
        }
    }
}
