package com.wxm.service.db.master.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxm.base.enums.DbSvcEnum;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.master.WxmUserGroup;
import com.wxm.druid.mapper.master.WxmUserGroupMapper;
import com.wxm.service.db.master.IWxmUserGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户-用户组关联表 服务实现类
 * </p>
 *
 * @author 王森明
 * @since 2022-06-17
 */
@Slf4j
@Service
public class WxmUserGroupServiceImpl extends ServiceImpl<WxmUserGroupMapper, WxmUserGroup> implements IWxmUserGroupService {
    private final WxmUserGroupMapper wxmUserGroupMapper;

    public WxmUserGroupServiceImpl(WxmUserGroupMapper wxmUserGroupMapper) {
        this.wxmUserGroupMapper = wxmUserGroupMapper;
    }

    @Override
    public int deleteBatchByGroupIdAndUserIds(Long groupId, List<Long> list) throws DbSvcException {
        try{
            return wxmUserGroupMapper.deleteBatchByGroupIdAndUserIds(groupId, list);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1004_A.toString(),DbSvcEnum.DB_SVC_1004_A.getMessage("根据用户组Id和用户Id列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1004_A.toString(),DbSvcEnum.DB_SVC_1004_A.getMessage("根据用户组Id和用户Id列表"),e.getMessage());
        }
    }

    @Override
    public int deleteBatchByUserList(List<Long> list) throws DbSvcException {
        try{
            return wxmUserGroupMapper.deleteBatchByUserIds(list);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1004_B.toString(),DbSvcEnum.DB_SVC_1004_B.getMessage("根据用户Id列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1004_B.toString(),DbSvcEnum.DB_SVC_1004_B.getMessage("根据用户Id列表"),e.getMessage());
        }
    }

    @Override
    public int deleteBatchByGroupIds(List<Long> list) throws DbSvcException {
        try{
            return wxmUserGroupMapper.deleteBatchByGroupIds(list);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1004_B.toString(),DbSvcEnum.DB_SVC_1004_B.getMessage("根据用户组Id列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1004_B.toString(),DbSvcEnum.DB_SVC_1004_B.getMessage("根据用户组Id列表"),e.getMessage());
        }
    }
}
