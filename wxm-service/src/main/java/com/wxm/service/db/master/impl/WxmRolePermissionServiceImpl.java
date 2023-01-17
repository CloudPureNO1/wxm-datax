package com.wxm.service.db.master.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxm.base.enums.DbSvcEnum;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.master.WxmRolePermission;
import com.wxm.druid.mapper.master.WxmRolePermissionMapper;
import com.wxm.service.db.master.IWxmRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色-权限关联表 服务实现类
 * </p>
 *
 * @author 王森明
 * @since 2022-06-17
 */
@Slf4j
@Service
public class WxmRolePermissionServiceImpl extends ServiceImpl<WxmRolePermissionMapper, WxmRolePermission> implements IWxmRolePermissionService {
    private final WxmRolePermissionMapper wxmRolePermissionMapper;

    public WxmRolePermissionServiceImpl(WxmRolePermissionMapper wxmRolePermissionMapper) {
        this.wxmRolePermissionMapper = wxmRolePermissionMapper;
    }

    @Override
    public int deleteBatchByRoleIdAndPermissionIds(Long roleId, List<Long> list) throws DbSvcException {
        try{
            return wxmRolePermissionMapper.deleteBatchByRoleIdAndPermissionIds(roleId,list);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1004_B.toString(),DbSvcEnum.DB_SVC_1004_B.getMessage("根据角色Id和权限Id列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1004_B.toString(),DbSvcEnum.DB_SVC_1004_B.getMessage("根据角色Id和权限Id列表"),e.getMessage());
        }
    }

    @Override
    public int deleteByRoleIds(List<Long> list) throws DbSvcException {
        try{
            return wxmRolePermissionMapper.deleteByRoleIds(list);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1004_B.toString(),DbSvcEnum.DB_SVC_1004_B.getMessage("根据角色Id列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1004_B.toString(),DbSvcEnum.DB_SVC_1004_B.getMessage("根据角色Id列表"),e.getMessage());
        }
    }

    @Override
    public int deleteByPermissionIds(List<Long> list) throws DbSvcException {
        try{
            return wxmRolePermissionMapper.deleteByPermissionIds(list);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1004_B.toString(),DbSvcEnum.DB_SVC_1004_B.getMessage("根据权限Id列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1004_B.toString(),DbSvcEnum.DB_SVC_1004_B.getMessage("根据权限Id列表"),e.getMessage());
        }
    }
}
