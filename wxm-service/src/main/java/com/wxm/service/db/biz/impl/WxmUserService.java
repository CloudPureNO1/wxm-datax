package com.wxm.service.db.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxm.base.dto.rbac.in.Rbac11002In;
import com.wxm.base.dto.rbac.in.Rbac11003In;
import com.wxm.base.enums.DbSvcEnum;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.biz.WxmUser;
import com.wxm.druid.mapper.biz.WxmUserMapper;

import com.wxm.service.db.biz.IWxmUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/6 10:06
 * @since 1.0.0
 */

@Slf4j
@Service("wxmUserService")
public class WxmUserService extends ServiceImpl<WxmUserMapper, WxmUser> implements IWxmUserService {

    private final WxmUserMapper wxmUserMapper;

    public WxmUserService(WxmUserMapper wxmUserMapper) {
        this.wxmUserMapper = wxmUserMapper;
    }

    @Override
    public List<WxmUser> queryAll() throws DbSvcException {
        try{
            return  wxmUserMapper.selectAll();
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("用户列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("用户列表"),e.getMessage());
        }
    }

    @Override
    public PageInfo queryAllPage(int currentPage, int pageSize) throws DbSvcException {
        try{
            PageHelper.startPage(currentPage,pageSize);
            List<WxmUser>list=wxmUserMapper.selectAll();
            PageInfo<WxmUser>pageInfo = new PageInfo<>(list);
            return pageInfo;
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("用户列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("用户列表"),e.getMessage());
        }
    }

    @Override
    public PageInfo queryPageByCon(Rbac11002In rbac11002In) throws DbSvcException {
        try{
            PageHelper.startPage(rbac11002In.getCurrentPage(),rbac11002In.getPageSize());
            Long userId=null;
            if(StringUtils.hasLength(rbac11002In.getUserId())){
                userId=Long.valueOf(rbac11002In.getUserId());;
            }
            Long groupId=null;
            if(StringUtils.hasLength(rbac11002In.getGroupId())){
                groupId=Long.valueOf(rbac11002In.getGroupId());
            }
            List<WxmUser>list=wxmUserMapper.selectByCon(userId,rbac11002In.getUsername(),rbac11002In.getCreator(),rbac11002In.getUserType(),rbac11002In.getUserRate(), rbac11002In.getUserStatus(),groupId);
            PageInfo<WxmUser>pageInfo = new PageInfo<>(list);
            return pageInfo;
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("用户列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("用户列表"),e.getMessage());
        }
    }

    @Override
    public PageInfo queryPageByConNonGroup(Rbac11003In rbac11003In) throws DbSvcException {
        try{
            PageHelper.startPage(rbac11003In.getCurrentPage(),rbac11003In.getPageSize());
            Long userId=null;
            if(StringUtils.hasLength(rbac11003In.getUserId())){
                userId=Long.valueOf(rbac11003In.getUserId());;
            }
            Long groupId=null;
            if(StringUtils.hasLength(rbac11003In.getGroupId())){
                groupId=Long.valueOf(rbac11003In.getGroupId());
            }
            List<WxmUser>list=wxmUserMapper.selectByConNonGroup(userId,rbac11003In.getUsername(),rbac11003In.getCreator(),rbac11003In.getUserType(),rbac11003In.getUserRate(), rbac11003In.getUserStatus(),groupId);
            PageInfo<WxmUser>pageInfo = new PageInfo<>(list);
            return pageInfo;
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("用户列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("用户列表"),e.getMessage());
        }
    }

    @Override
    public int add(WxmUser user) throws DbSvcException {
        try{
            return wxmUserMapper.insert(user);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1002.toString(),DbSvcEnum.DB_SVC_1002.getMessage("用户"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1002.toString(),DbSvcEnum.DB_SVC_1002.getMessage("用户"),e.getMessage());
        }
    }

    @Override
    public int edit(WxmUser user) throws DbSvcException {
        try{
            return wxmUserMapper.updateById(user);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1003.toString(),DbSvcEnum.DB_SVC_1003.getMessage("用户"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1003.toString(),DbSvcEnum.DB_SVC_1003.getMessage("用户"),e.getMessage());
        }
    }

    @Override
    public int delete(Long userId) throws DbSvcException {
        try{
            return wxmUserMapper.deleteById(userId);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1004.toString(),DbSvcEnum.DB_SVC_1004.getMessage("用户"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1004.toString(),DbSvcEnum.DB_SVC_1004.getMessage("用户"),e.getMessage());
        }
    }

}
