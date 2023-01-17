package com.wxm.service.db.master.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxm.base.enums.DbSvcEnum;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.master.WxmGroupRole;
import com.wxm.druid.mapper.master.WxmGroupRoleMapper;
import com.wxm.service.db.master.IWxmGroupRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户组-角色关联表 服务实现类
 * </p>
 *
 * @author 王森明
 * @since 2022-06-17
 */
@Slf4j
@Service
public class WxmGroupRoleServiceImpl extends ServiceImpl<WxmGroupRoleMapper, WxmGroupRole> implements IWxmGroupRoleService {
    private final WxmGroupRoleMapper wxmGroupRoleMapper;

    public WxmGroupRoleServiceImpl(WxmGroupRoleMapper wxmGroupRoleMapper) {
        this.wxmGroupRoleMapper = wxmGroupRoleMapper;
    }

    @Override
    public int deleteByGroupIdAndRoleIds(Long groupId, List<Long> list) throws DbSvcException {
        try{
             return wxmGroupRoleMapper.deleteByGroupIdAndRoleIds(groupId, list);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1004_A.toString(),DbSvcEnum.DB_SVC_1004_A.getMessage("根据用户组Id和角色Id列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1004_A.toString(),DbSvcEnum.DB_SVC_1004_A.getMessage("根据用户组Id和角色Id列表"),e.getMessage());
        }
    }

    @Override
    public int deleteByGroupIds(List<Long> list) throws DbSvcException {
        try{
            return wxmGroupRoleMapper.deleteByGroupIds(list);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1004_B.toString(),DbSvcEnum.DB_SVC_1004_B.getMessage("根据用户组Id列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1004_B.toString(),DbSvcEnum.DB_SVC_1004_B.getMessage("根据用户组Id列表"),e.getMessage());
        }
    }

    @Override
    public int deleteByRoleIds(List<Long> list) throws DbSvcException {
        try{
            return wxmGroupRoleMapper.deleteByRoleIds(list);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1004_B.toString(),DbSvcEnum.DB_SVC_1004_B.getMessage("根据用户组Id列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1004_B.toString(),DbSvcEnum.DB_SVC_1004_B.getMessage("根据用户组Id列表"),e.getMessage());
        }
    }
}
