package com.wxm.service.db.master.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxm.base.dto.rbac.in.Rbac51002In;
import com.wxm.base.dto.rbac.in.Rbac51003In;
import com.wxm.base.dto.rbac.in.Rbac51005In;
import com.wxm.base.enums.DbSvcEnum;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.master.WxmGroup;
import com.wxm.druid.entity.master.WxmPermission;
import com.wxm.druid.mapper.master.WxmPermissionMapper;
import com.wxm.service.db.master.IWxmPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 资源的权限表（资源中的按钮和其他功能） 服务实现类
 * </p>
 *
 * @author 王森明
 * @since 2022-06-17
 */
@Slf4j
@Service
public class WxmPermissionServiceImpl extends ServiceImpl<WxmPermissionMapper, WxmPermission> implements IWxmPermissionService {
    private final WxmPermissionMapper wxmPermissionMapper;

    public WxmPermissionServiceImpl(WxmPermissionMapper wxmPermissionMapper) {
        this.wxmPermissionMapper = wxmPermissionMapper;
    }

    @Override
    public PageInfo queryAllPage(int currentPage, int pageSize) throws DbSvcException {
        try{
            PageHelper.startPage(currentPage,pageSize);

            List<WxmPermission> list=  wxmPermissionMapper.selectAll();
            PageInfo<WxmPermission>pageInfo=new PageInfo<>(list);
            return pageInfo;
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("权限列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("权限列表"),e.getMessage());
        }
    }

    @Override
    public PageInfo queryPageByCon(Rbac51002In rbac51002In) throws DbSvcException {
        try{
            PageHelper.startPage(rbac51002In.getCurrentPage(),rbac51002In.getPageSize());
            Long roleId=null;
            if(StringUtils.hasLength(rbac51002In.getRoleId())){
                roleId=Long.valueOf(rbac51002In.getRoleId());;
            }
//            Long resourceId=null;
//            if(StringUtils.hasLength(rbac51002In.getResourceId())){
//                resourceId=Long.valueOf(rbac51002In.getResourceId());
//            }
            Long permissionId=null;
            if(StringUtils.hasLength(rbac51002In.getPermissionId())){
                permissionId=Long.valueOf(rbac51002In.getPermissionId());
            }
            List<WxmPermission> list=  wxmPermissionMapper.selectByCon(permissionId,rbac51002In.getPermissionName(),rbac51002In.getPermissionCode(),rbac51002In.getCreator(),rbac51002In.getPermissionType(),rbac51002In.getPermissionStatus(),roleId);
            return new PageInfo<>(list);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("权限列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("权限列表"),e.getMessage());
        }
    }

    @Override
    public PageInfo queryPageByConNonRole(Rbac51003In rbac51003In) throws DbSvcException {
        try{
            PageHelper.startPage(rbac51003In.getCurrentPage(),rbac51003In.getPageSize());
            Long roleId=null;
            if(StringUtils.hasLength(rbac51003In.getRoleId())){
                roleId=Long.valueOf(rbac51003In.getRoleId());;
            }
            Long resourceId=null;
            if(StringUtils.hasLength(rbac51003In.getResourceId())){
                resourceId=Long.valueOf(rbac51003In.getResourceId());
            }
            Long permissionId=null;
            if(StringUtils.hasLength(rbac51003In.getPermissionId())){
                permissionId=Long.valueOf(rbac51003In.getPermissionId());
            }
            List<WxmPermission> list=  wxmPermissionMapper.selectByConNonRole(permissionId,rbac51003In.getPermissionName(),rbac51003In.getPermissionCode(),rbac51003In.getCreator(),rbac51003In.getPermissionType(),rbac51003In.getPermissionStatus(),resourceId,roleId);
            return new PageInfo<>(list);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("权限列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("权限列表"),e.getMessage());
        }
    }

    @Override
    public PageInfo queryPageByConAboutRole(Rbac51005In rbac51005In) throws DbSvcException {
        try{
            PageHelper.startPage(rbac51005In.getCurrentPage(),rbac51005In.getPageSize());
            Long roleId=StringUtils.hasLength(rbac51005In.getRoleId())?Long.valueOf(rbac51005In.getRoleId()):null;
            Long permissionId=StringUtils.hasLength(rbac51005In.getPermissionId())?Long.valueOf(rbac51005In.getPermissionId()):null;
            String permissionCode=rbac51005In.getPermissionCode();
            String permissionName=rbac51005In.getPermissionName();
            List<WxmPermission> list=  wxmPermissionMapper.selectByConAboutRole(permissionId,permissionName,permissionCode,roleId);
            return new PageInfo<>(list);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("权限列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("权限列表"),e.getMessage());
        }
    }
}
