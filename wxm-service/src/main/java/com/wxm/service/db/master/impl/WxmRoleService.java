package com.wxm.service.db.master.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxm.base.dto.rbac.in.Rbac31002In;
import com.wxm.base.dto.rbac.in.Rbac31003In;
import com.wxm.base.enums.DbSvcEnum;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.master.WxmRole;
import com.wxm.druid.mapper.master.WxmRoleMapper;
import com.wxm.service.db.master.IWxmRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/6 10:46
 * @since 1.0.0
 */
@Slf4j
@Service("wxmRoleService")
public class WxmRoleService extends ServiceImpl<WxmRoleMapper, WxmRole> implements IWxmRoleService {
    private final WxmRoleMapper wxmRoleMapper;

    public WxmRoleService(WxmRoleMapper wxmRoleMapper) {
        this.wxmRoleMapper = wxmRoleMapper;
    }

    @Override
    public List<WxmRole> queryAll() throws DbSvcException {
        try {
            return wxmRoleMapper.selectAll();
        } catch (Exception e) {
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("角色列表"), e.getMessage(), e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("角色列表"), e.getMessage());
        }
    }

    @Override
    public PageInfo<WxmRole> queryAllPage(int currentPage, int pageSize) throws DbSvcException {
        try {
            PageHelper.startPage(currentPage, pageSize);
            List<WxmRole> list = wxmRoleMapper.selectAll();
            PageInfo<WxmRole> pageInfo = new PageInfo<>(list);
            return pageInfo;
        } catch (Exception e) {
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("角色列表"), e.getMessage(), e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("角色列表"), e.getMessage());
        }
    }

    @Override
    public PageInfo<WxmRole> queryPageByCon(Rbac31002In rbac11002In) throws DbSvcException {
        try {
            PageHelper.startPage(rbac11002In.getCurrentPage(), rbac11002In.getPageSize());
            Long roleId = null;
            if (StringUtils.hasLength(rbac11002In.getRoleId())) {
                roleId = Long.valueOf(rbac11002In.getRoleId());
            }
            Long groupId = null;
            if (StringUtils.hasLength(rbac11002In.getGroupId())) {
                groupId = Long.valueOf(rbac11002In.getGroupId());
            }
            List<WxmRole> list = wxmRoleMapper.selectByCon(roleId, rbac11002In.getRoleName(), rbac11002In.getRoleCode(), rbac11002In.getCreator(), rbac11002In.getRoleType(), rbac11002In.getRoleStatus(), groupId);
            PageInfo<WxmRole> pageInfo = new PageInfo<>(list);
            return pageInfo;
        } catch (Exception e) {
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("角色列表"), e.getMessage(), e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("角色列表"), e.getMessage());
        }
    }

    @Override
    public PageInfo<WxmRole> queryPageByConNonGroup(Rbac31003In rbac11003In) throws DbSvcException {
        try {
            PageHelper.startPage(rbac11003In.getCurrentPage(), rbac11003In.getPageSize());
            Long roleId = null;
            if (StringUtils.hasLength(rbac11003In.getRoleId())) {
                roleId = Long.valueOf(rbac11003In.getRoleId());
            }
            Long groupId = null;
            if (StringUtils.hasLength(rbac11003In.getGroupId())) {
                groupId = Long.valueOf(rbac11003In.getGroupId());
            }
            List<WxmRole> list = wxmRoleMapper.selectByConNonGroup(roleId, rbac11003In.getRoleName(), rbac11003In.getRoleCode(), rbac11003In.getCreator(), rbac11003In.getRoleType(), rbac11003In.getRoleStatus(), groupId);
            PageInfo<WxmRole> pageInfo = new PageInfo<>(list);
            return pageInfo;
        } catch (Exception e) {
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("角色列表"), e.getMessage(), e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("角色列表"), e.getMessage());
        }
    }

    @Override
    public int add(WxmRole role) throws DbSvcException {
        try {
            return wxmRoleMapper.insert(role);
        } catch (Exception e) {
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1002.toString(), DbSvcEnum.DB_SVC_1002.getMessage("角色"), e.getMessage(), e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1002.toString(), DbSvcEnum.DB_SVC_1002.getMessage("角色"), e.getMessage());
        }
    }

    @Override
    public int edit(WxmRole role) throws DbSvcException {
        try {
            return wxmRoleMapper.updateById(role);
        } catch (Exception e) {
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1003.toString(), DbSvcEnum.DB_SVC_1003.getMessage("角色"), e.getMessage(), e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1003.toString(), DbSvcEnum.DB_SVC_1003.getMessage("角色"), e.getMessage());
        }
    }

    @Override
    public int delete(Long roleId) throws DbSvcException {
        try {
            return wxmRoleMapper.deleteById(roleId);
        } catch (Exception e) {
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1004.toString(), DbSvcEnum.DB_SVC_1004.getMessage("角色"), e.getMessage(), e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1004.toString(), DbSvcEnum.DB_SVC_1004.getMessage("角色"), e.getMessage());
        }
    }

    @Override
    public List<WxmRole> queryByResourceUrl(String resourceUrl) throws DbSvcException {
        try {
            return wxmRoleMapper.selectByResourceUrl(resourceUrl);
        } catch (Exception e) {
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("角色"), e.getMessage(), e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("角色"), e.getMessage());
        }
    }

    @Override
    public List<WxmRole> queryByApiUrl(String apiUrl) throws DbSvcException {
        try {
            return wxmRoleMapper.selectByApiUrl(apiUrl);
        } catch (Exception e) {
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("角色"), e.getMessage(), e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("角色"), e.getMessage());
        }
    }

    @Override
    public List<WxmRole> findRolesByUsername(String username) throws DbSvcException {
        try {
            return wxmRoleMapper.findRolesByUsername(username);
        } catch (Exception e) {
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("角色"), e.getMessage(), e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("角色"), e.getMessage());
        }
    }
}
