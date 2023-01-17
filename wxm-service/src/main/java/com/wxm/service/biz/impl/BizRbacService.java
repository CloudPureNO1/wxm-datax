package com.wxm.service.biz.impl;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.wxm.base.dto.TreeNode;
import com.wxm.base.dto.rbac.in.*;
import com.wxm.base.dto.rbac.out.*;
import com.wxm.base.dto.rbac.out.item.*;
import com.wxm.base.enums.BizSvcEnum;
import com.wxm.base.exception.BizSvcException;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.master.*;
import com.wxm.service.biz.IBizRbacService;
import com.wxm.service.biz.Template;
import com.wxm.service.biz.TemplateWithParams;
import com.wxm.service.db.master.*;
import com.wxm.util.my.BeanUtils;
import com.wxm.util.my.TreeUtils;
import com.wxm.util.my.code.SM3Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/8 13:35
 * @since 1.0.0
 */
@Slf4j
@Service("rbacService")
public class BizRbacService implements IBizRbacService {
    private final IWxmUserService wxmUserService;
    private final IWxmGroupService wxmGroupService;
    private final IWxmRoleService wxmRoleService;
    private final IWxmResourceService wxmResourceService;
    private final IWxmDictionaryService wxmDictionaryService;
    private final IWxmPermissionService wxmPermissionService;
    private final IWxmUserGroupService wxmUserGroupService;
    private final IWxmGroupRoleService wxmGroupRoleService;
    private final IWxmRoleResourceService wxmRoleResourceService;
    private final IWxmRolePermissionService wxmRolePermissionService;
    private final IWxmApiService wxmApiService;
    private final IWxmRoleApiService wxmRoleApiService;

    public BizRbacService(IWxmUserService wxmUserService, IWxmGroupService wxmGroupService, IWxmRoleService wxmRoleService, IWxmResourceService wxmResourceService, IWxmDictionaryService wxmDictionaryService, IWxmPermissionService wxmPermissionService, IWxmUserGroupService wxmUserGroupService, IWxmGroupRoleService wxmGroupRoleService, IWxmRoleResourceService wxmRoleResourceService, IWxmRolePermissionService wxmRolePermissionService, IWxmApiService wxmApiService, IWxmRoleApiService wxmRoleApiService) {
        this.wxmUserService = wxmUserService;
        this.wxmGroupService = wxmGroupService;
        this.wxmRoleService = wxmRoleService;
        this.wxmResourceService = wxmResourceService;
        this.wxmDictionaryService = wxmDictionaryService;
        this.wxmPermissionService = wxmPermissionService;
        this.wxmUserGroupService = wxmUserGroupService;
        this.wxmGroupRoleService = wxmGroupRoleService;
        this.wxmRoleResourceService = wxmRoleResourceService;
        this.wxmRolePermissionService = wxmRolePermissionService;
        this.wxmApiService = wxmApiService;
        this.wxmRoleApiService = wxmRoleApiService;
    }


    @Override
    public List<Rbac11001Out> service11001(Rbac11001In rbac11001In) throws BizSvcException, DbSvcException {
        Service11001 service11001 = new Service11001();
        return service11001.service(WxmUser.class, Rbac11001Out.class, "用户列表");
    }

    @Override
    public Rbac11002Out service11002(Rbac11002In rbac11002In) throws BizSvcException, DbSvcException {
/*        try {
            PageInfo<WxmUser> pageInfo = wxmUserService.queryAllPage(rbac11002In.getCurrentPage(), rbac11002In.getPageSize());
            List<WxmUser> listSource = pageInfo.getList();
            // 单例模式
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier beanCopier = instance.create(WxmUser.class, Item11002Out.class, true);

            List<Item11002Out> list = listSource.stream().map(source -> {
                Item11002Out item11002Out = new Item11002Out();
                instance.copy(source, item11002Out, beanCopier, false);
                return item11002Out;
            }).collect(Collectors.toList());
            Rbac11002Out rbac11002Out = new Rbac11002Out();
            rbac11002Out.setList(list);
            rbac11002Out.setTotal(pageInfo.getTotal());
            return rbac11002Out;
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("用户列表"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("用户列表"),e.getMessage());
        }*/
        Service11002 service11002 = new Service11002();
        return service11002.service(rbac11002In, WxmUser.class, Item11002Out.class, "用户列表");

    }

    @Override
    public Rbac11003Out service11003(Rbac11003In rbac11003In) throws BizSvcException, DbSvcException {
        Service11003 service11003 = new Service11003();
        return service11003.service(rbac11003In, WxmUser.class, Item11003Out.class, "用户列表");
    }

    @DSTransactional
    @Override
    public Rbac12001Out service12001(Rbac12001In rbac12001In) throws BizSvcException, DbSvcException {
        try {
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bcIn = instance.create(Rbac12001In.class, WxmUser.class, true);
            WxmUser user = new WxmUser();
            // String -> 其他类型包含String
            instance.copy(rbac12001In, user, bcIn, true);
            user.setPasswd(SM3Util.encode(user.getPasswd()));
            Date date = new Date();
            user.setCreateTime(date);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            user.setCreator(userDetail.getUsername());
            user.setOperator(userDetail.getUsername());
            user.setUpdateTime(date);
            wxmUserService.add(user);

            Rbac12001Out out = new Rbac12001Out();
            //其他类型-> String
            BeanCopier bcOut = instance.create(WxmUser.class, Rbac12001Out.class, true);
            instance.copy(user, out, bcOut, false);
            return out;
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("用户"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("用户"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac13001Out service13001(Rbac13001In rbac13001In) throws BizSvcException, DbSvcException {
        try {
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bcIn = instance.create(Rbac13001In.class, WxmUser.class, true);
            WxmUser user = new WxmUser();
            // String -> 其他类型包含String
            instance.copy(rbac13001In, user, bcIn, true);
            if (StringUtils.hasLength(rbac13001In.getPasswdNew())) {
                user.setPasswd(SM3Util.encode(rbac13001In.getPasswdNew()));
            }
            Date date = new Date();
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            user.setOperator(userDetail.getUsername());
            user.setUpdateTime(date);
            wxmUserService.edit(user);

            Rbac13001Out out = new Rbac13001Out();
            //其他类型-> String
            BeanCopier bcOut = instance.create(WxmUser.class, Rbac13001Out.class, true);
            instance.copy(user, out, bcOut, false);
            return out;
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1003.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("用户"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1003.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("用户"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac14001Out service14001(Rbac14001In rbac14001In) throws BizSvcException, DbSvcException {
        try {
            wxmUserService.delete(Long.valueOf(rbac14001In.getUserId()));
            // 删除用户、删除用户管理  wxm_user_group
            QueryWrapper<WxmUserGroup> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", Long.valueOf(rbac14001In.getUserId()));
            wxmUserGroupService.remove(queryWrapper);
            return new Rbac14001Out(rbac14001In.getUserId());
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004_A.toString(), BizSvcEnum.BIZ_SVC_1004_A.getMessage("用户"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004_A.toString(), BizSvcEnum.BIZ_SVC_1004_A.getMessage("用户"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac14002Out service14002(Rbac14002In rbac14002In) throws BizSvcException, DbSvcException {
        try {
            List<Long> list = rbac14002In.getList().stream().map(userId -> Long.valueOf(userId)).collect(Collectors.toList());
            wxmUserService.removeBatchByIds(list);
            wxmUserGroupService.deleteBatchByUserList(list);
            return new Rbac14002Out(rbac14002In.getList());
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004_B.toString(), BizSvcEnum.BIZ_SVC_1004_B.getMessage("用户"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004_B.toString(), BizSvcEnum.BIZ_SVC_1004_B.getMessage("用户"), e.getMessage());
        }
    }

    @Override
    public List<Rbac21001Out> service21001(Rbac21001In rbac21001In) throws BizSvcException, DbSvcException {
        // 采用设计模式之：模板模式实现：
        Service21001 service21001 = new Service21001();
        return service21001.service(WxmGroup.class, Rbac21001Out.class, "用户组列表");
    }

    @Override
    public Rbac21002Out service21002(Rbac21002In rbac21002In) throws BizSvcException, DbSvcException {
/*        try {
            PageInfo<WxmGroup> pageInfo = wxmGroupService.queryPageByCon(rbac21002In);
            List<WxmGroup> listSource = pageInfo.getList();
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(WxmGroup.class, Item21002Out.class, true);
            List<Item21002Out> list = listSource.stream().map(source -> {
                Item21002Out item21002Out = new Item21002Out();
                instance.copy(source, item21002Out, bc, false);
                return item21002Out;
            }).collect(Collectors.toList());
            Rbac21002Out out = new Rbac21002Out();
            out.setList(list);
            out.setTotal(pageInfo.getTotal());
            return out;
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("用户组列表"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("用户组列表"), e.getMessage());
        }*/
// 采用模板模式实现
        Service21002 service21002 = new Service21002();
        return service21002.service(rbac21002In, WxmGroup.class, Item21002Out.class, "用户组列表");
    }

    @DSTransactional
    @Override
    public Rbac22001Out service22001(Rbac22001In rbac22001In) throws BizSvcException, DbSvcException {
        try {
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(Rbac22001In.class, WxmGroup.class, true);
            WxmGroup group = new WxmGroup();
            instance.copy(rbac22001In, group, bc, true);
            Date date = new Date();
            group.setGroupCode("G-" + UUID.randomUUID().toString().replaceAll("-", ""));
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            group.setCreator(userDetail.getUsername());
            group.setOperator(userDetail.getUsername());
            group.setCreateTime(date);
            group.setUpdateTime(date);
            wxmGroupService.add(group);
            bc = instance.create(WxmGroup.class, Rbac22001Out.class, true);
            Rbac22001Out out = new Rbac22001Out();
            instance.copy(group, out, bc, false);
            return out;
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("用户组"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("用户组"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac22002Out service22002(Rbac22002In rbac22002In) throws BizSvcException, DbSvcException {
        try {
            List<String> list = rbac22002In.getList();
            if (CollectionUtils.isEmpty(list)) {
                throw new BizSvcException("传入的用户列表为空");
            }
            if (!StringUtils.hasLength(rbac22002In.getGroupId())) {
                throw new BizSvcException("传入的用户组ID为空");
            }
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            String username = userDetail.getUsername();
            List<WxmUserGroup> listEntity = list.stream().map(userId -> {
                WxmUserGroup userGroup = new WxmUserGroup();
                userGroup.setGroupId(Long.valueOf(rbac22002In.getGroupId()));
                userGroup.setUserId(Long.valueOf(userId));
                userGroup.setCreator(username);
                userGroup.setCreateTime(new Date());
                return userGroup;
            }).collect(Collectors.toList());
            wxmUserGroupService.saveBatch(listEntity);
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(Rbac22002In.class, Rbac22002Out.class, true);
            Rbac22002Out out = new Rbac22002Out();
            instance.copy(rbac22002In, out, bc, false);
            return out;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1002_A.toString(), BizSvcEnum.BIZ_SVC_1002_A.getMessage("给用户组添加用户"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1002_A.toString(), BizSvcEnum.BIZ_SVC_1002_A.getMessage("给用户组添加用户"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac22003Out service22003(Rbac22003In rbac22003In) throws BizSvcException, DbSvcException {
        try {
            List<String> list = rbac22003In.getList();
            if (CollectionUtils.isEmpty(list)) {
                throw new BizSvcException("传入的角色列表为空");
            }
            if (!StringUtils.hasLength(rbac22003In.getGroupId())) {
                throw new BizSvcException("传入的用户组ID为空");
            }
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            String username = userDetail.getUsername();
            List<WxmGroupRole> listEntity = list.stream().map(roleId -> {
                WxmGroupRole groupRole = new WxmGroupRole();
                groupRole.setGroupId(Long.valueOf(rbac22003In.getGroupId()));
                groupRole.setRoleId(Long.valueOf(roleId));
                groupRole.setCreator(username);
                groupRole.setCreateTime(new Date());
                return groupRole;
            }).collect(Collectors.toList());
            wxmGroupRoleService.saveBatch(listEntity);
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(Rbac22003In.class, Rbac22003Out.class, true);
            Rbac22003Out out = new Rbac22003Out();
            instance.copy(rbac22003In, out, bc, false);
            return out;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1002_A.toString(), BizSvcEnum.BIZ_SVC_1002_A.getMessage("给用户组添加角色"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1002_A.toString(), BizSvcEnum.BIZ_SVC_1002_A.getMessage("给用户组添加角色"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac23001Out service23001(Rbac23001In rbac23001In) throws BizSvcException, DbSvcException {
        try {
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(Rbac23001In.class, WxmGroup.class, true);
            WxmGroup group = new WxmGroup();
            instance.copy(rbac23001In, group, bc, true);
            Date date = new Date();
            group.setUpdateTime(date);
            UserDetails userDetails= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            group.setOperator(userDetails.getUsername());
            wxmGroupService.edit(group);
            bc = instance.create(WxmGroup.class, Rbac23001Out.class, true);
            Rbac23001Out out = new Rbac23001Out();
            instance.copy(group, out, bc, false);
            return out;
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1003.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("用户组"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1003.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("用户组"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac24001Out service24001(Rbac24001In rbac24001In) throws BizSvcException, DbSvcException {
        try {
            wxmGroupService.delete(Long.valueOf(rbac24001In.getGroupId()));
            // 用户组删除，删除对应的关联数据  wxm_user_group  wxm_group_role
            QueryWrapper<WxmUserGroup> wrapperUG = new QueryWrapper<>();
            wrapperUG.eq("group_id", Long.valueOf(rbac24001In.getGroupId()));
            wxmUserGroupService.remove(wrapperUG);
            QueryWrapper<WxmGroupRole> wrapperGR = new QueryWrapper<>();
            wrapperGR.eq("group_id", Long.valueOf(rbac24001In.getGroupId()));
            wxmGroupRoleService.remove(wrapperGR);
            return new Rbac24001Out(rbac24001In.getGroupId());
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("用户组"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("用户组"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac24002Out service24002(Rbac24002In rbac24002In) throws BizSvcException, DbSvcException {
        try {
            List<String> list = rbac24002In.getList();
            if (CollectionUtils.isEmpty(list)) {
                throw new BizSvcException("传入的用户列表为空");
            }
            if (!StringUtils.hasLength(rbac24002In.getGroupId())) {
                throw new BizSvcException("传入的用户组ID为空");
            }
            List<Long> listUserId = list.stream().map(userId -> Long.valueOf(userId)).collect(Collectors.toList());
            wxmUserGroupService.deleteBatchByGroupIdAndUserIds(Long.valueOf(rbac24002In.getGroupId()), listUserId);
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(Rbac24002In.class, Rbac24002Out.class, true);
            Rbac24002Out out = new Rbac24002Out();
            instance.copy(rbac24002In, out, bc, false);
            return out;
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004_A.toString(), BizSvcEnum.BIZ_SVC_1004_A.getMessage("把用户从用户组中移除"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004_A.toString(), BizSvcEnum.BIZ_SVC_1004_A.getMessage("把用户从用户组中移除"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac24003Out service24003(Rbac24003In rbac24003In) throws BizSvcException, DbSvcException {
        try {
            List<String> list = rbac24003In.getList();
            if (CollectionUtils.isEmpty(list)) {
                throw new BizSvcException("传入的角色列表为空");
            }
            if (!StringUtils.hasLength(rbac24003In.getGroupId())) {
                throw new BizSvcException("传入的用户组ID为空");
            }
            List<Long> listRoleId = list.stream().map(roleId -> Long.valueOf(roleId)).collect(Collectors.toList());
            wxmGroupRoleService.deleteByGroupIdAndRoleIds(Long.valueOf(rbac24003In.getGroupId()), listRoleId);
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(Rbac24003In.class, Rbac24003Out.class, true);
            Rbac24003Out out = new Rbac24003Out();
            instance.copy(rbac24003In, out, bc, false);
            return out;
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004_A.toString(), BizSvcEnum.BIZ_SVC_1004_A.getMessage("把用户从用户组中移除"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004_A.toString(), BizSvcEnum.BIZ_SVC_1004_A.getMessage("把用户从用户组中移除"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac24004Out service24004(Rbac24004In rbac24004In) throws BizSvcException, DbSvcException {
        try {
            List<Long> list = rbac24004In.getList().stream().map(groupId -> Long.valueOf(groupId)).collect(Collectors.toList());
            wxmGroupService.removeByIds(list);
            // 用户组删除，删除对应的关联数据  wxm_user_group  wxm_group_role
            wxmUserGroupService.deleteBatchByGroupIds(list);
            wxmGroupRoleService.deleteByGroupIds(list);
            return new Rbac24004Out(rbac24004In.getList());
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004_B.toString(), BizSvcEnum.BIZ_SVC_1004_B.getMessage("用户组"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004_B.toString(), BizSvcEnum.BIZ_SVC_1004_B.getMessage("用户组"), e.getMessage());
        }
    }

    @Override
    public List<Rbac31001Out> service31001(Rbac31001In rbac31001In) throws BizSvcException, DbSvcException {
        // 采用设计模式之：模板模式实现：
        Service31001 service31001 = new Service31001();
        return service31001.service(WxmDictionary.class, Rbac31001Out.class, "角色列表");
    }

    @Override
    public Rbac31002Out service31002(Rbac31002In rbac31002In) throws BizSvcException, DbSvcException {
        try {
            Rbac31002Out out = new Rbac31002Out();
            PageInfo<WxmRole> pageInfo = wxmRoleService.queryPageByCon(rbac31002In);
            List<WxmRole> listSource = pageInfo.getList();
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(WxmRole.class, Item31002Out.class, true);
            if (!CollectionUtils.isEmpty(listSource)) {
                List<Item31002Out> list = listSource.stream().map(source -> {
                    Item31002Out item31002Out = new Item31002Out();
                    instance.copy(source, item31002Out, bc, false);
                    return item31002Out;
                }).collect(Collectors.toList());
                out.setList(list);
            }
            out.setTotal(pageInfo.getTotal());
            return out;
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("角色列表"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("角色列表"), e.getMessage());
        }
    }

    @Override
    public Rbac31003Out service31003(Rbac31003In rbac31003In) throws BizSvcException, DbSvcException {
        try {
            Rbac31003Out out = new Rbac31003Out();
            PageInfo<WxmRole> pageInfo = wxmRoleService.queryPageByConNonGroup(rbac31003In);
            List<WxmRole> listSource = pageInfo.getList();
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(WxmRole.class, Item31003Out.class, true);
            if (!CollectionUtils.isEmpty(listSource)) {
                List<Item31003Out> list = listSource.stream().map(source -> {
                    Item31003Out item31003Out = new Item31003Out();
                    instance.copy(source, item31003Out, bc, false);
                    return item31003Out;
                }).collect(Collectors.toList());
                out.setList(list);
            }
            out.setTotal(pageInfo.getTotal());
            return out;
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("角色列表"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("角色列表"), e.getMessage());
        }
    }

    @Override
    public Rbac31004Out service31004(@Valid Rbac31004In rbac31004In) throws BizSvcException {
        try {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            List<SimpleGrantedAuthority> simpleGrantedAuthorityList =  (List<SimpleGrantedAuthority>)userDetail.getAuthorities();
            List<String>roleList= simpleGrantedAuthorityList.stream().map(item->item.getAuthority()).collect(Collectors.toList());
            return new Rbac31004Out().setList(roleList);
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("当前登录用户角色列表"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("当前登录用户角色列表"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac32001Out service32001(Rbac32001In rbac32001In) throws BizSvcException, DbSvcException {
        try {
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(Rbac32001In.class, WxmRole.class, true);
            WxmRole role = new WxmRole();
            instance.copy(rbac32001In, role, bc, true);
            Date date = new Date();
            // role.setRoleCode("R-" + UUID.randomUUID().toString().replaceAll("-", ""));
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            ;
            String username = userDetail.getUsername();
            role.setCreator(username);
            role.setCreateTime(date);
            role.setUpdateTime(date);
            wxmRoleService.add(role);
            bc = instance.create(WxmRole.class, Rbac32001Out.class, true);
            Rbac32001Out out = new Rbac32001Out();
            instance.copy(role, out, bc, false);
            return out;
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("角色"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("角色"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac32002Out service32002(Rbac32002In rbac32002In) throws BizSvcException, DbSvcException {
        try {
            if (!StringUtils.hasLength(rbac32002In.getRoleId())) {
                throw new BizSvcException("角色ID不能为空");
            }
            List<String> list = rbac32002In.getList();
            if (CollectionUtils.isEmpty(list)) {
                throw new BizSvcException("传入的资源ID列表为空");
            }
            List<WxmRoleResource> listAdd = new ArrayList<>();
            // 授权不存在编辑
            // List<WxmRoleResource>listUpdate=new ArrayList<>();
            List<WxmRoleResource> listDelete = new ArrayList<>();
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("role_id", Long.valueOf(rbac32002In.getRoleId()));
            List<WxmRoleResource> listDb = wxmRoleResourceService.list(queryWrapper);
            Date date = new Date();
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            ;
            String username = userDetail.getUsername();
            if (CollectionUtils.isEmpty(listDb)) {
                listAdd = list.stream().map(resourceId -> {
                    WxmRoleResource roleResource = new WxmRoleResource();
                    roleResource.setRoleId(Long.valueOf(rbac32002In.getRoleId()));
                    roleResource.setResourceId(Long.valueOf(resourceId));
                    roleResource.setCreator(username);
                    roleResource.setCreateTime(date);
                    return roleResource;
                }).collect(Collectors.toList());
                wxmRoleResourceService.saveBatch(listAdd);
                return new Rbac32002Out(rbac32002In.getRoleId(), list);
            }
            listAdd = list.stream().filter(resourceId -> listDb.stream().noneMatch(item -> resourceId.equals(item.getResourceId().toString()))).map(id -> {
                WxmRoleResource roleResource = new WxmRoleResource();
                roleResource.setRoleId(Long.valueOf(rbac32002In.getRoleId()));
                roleResource.setResourceId(Long.valueOf(id));
                roleResource.setCreator(username);
                roleResource.setCreateTime(date);
                return roleResource;
            }).collect(Collectors.toList());
            listDelete = listDb.stream().filter(item -> list.stream().noneMatch(resourceId -> resourceId.equals(item.getResourceId().toString()))).collect(Collectors.toList());
            wxmRoleResourceService.saveBatch(listAdd);
            wxmRoleResourceService.removeBatchByIds(listDelete.stream().map(item -> item.getRoleResourceId()).collect(Collectors.toList()));
            return new Rbac32002Out(rbac32002In.getRoleId(), list);
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1005.toString(), BizSvcEnum.BIZ_SVC_1005.getMessage("角色"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1005.toString(), BizSvcEnum.BIZ_SVC_1005.getMessage("角色"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac32003Out service32003(Rbac32003In rbac32003In) throws BizSvcException, DbSvcException {
        try {
            List<String> list = rbac32003In.getList();
            if (CollectionUtils.isEmpty(list)) {
                throw new BizSvcException("传入的权限列表为空");
            }
            if (!StringUtils.hasLength(rbac32003In.getRoleId())) {
                throw new BizSvcException("传入的角色ID为空");
            }
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            ;
            String username = userDetail.getUsername();
            List<WxmRolePermission> entityList = list.stream().map(permissionId -> {
                WxmRolePermission rolePermission = new WxmRolePermission();
                rolePermission.setPermissionId(Long.valueOf(permissionId));
                rolePermission.setRoleId(Long.valueOf(rbac32003In.getRoleId()));
                rolePermission.setCreator(username);
                rolePermission.setCreateTime(new Date());
                return rolePermission;
            }).collect(Collectors.toList());
            wxmRolePermissionService.saveBatch(entityList);
            return new Rbac32003Out(rbac32003In.getRoleId(), rbac32003In.getList());
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("给角色添加权限"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("给角色添加权限"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac32004Out service32004(Rbac32004In rbac32004In) throws BizSvcException, DbSvcException {
        try {
            List<String> list = rbac32004In.getList();
            if (CollectionUtils.isEmpty(list)) {
                throw new BizSvcException("传入的接口列表为空");
            }
            if (!StringUtils.hasLength(rbac32004In.getRoleId())) {
                throw new BizSvcException("传入的角色ID为空");
            }
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            ;
            String username = userDetail.getUsername();
            List<WxmRoleApi> entityList = list.stream().map(apiId -> {
                WxmRoleApi roleApi = new WxmRoleApi();
                roleApi.setRoleId(Long.valueOf(rbac32004In.getRoleId()));
                roleApi.setApiId(Long.valueOf(apiId));
                roleApi.setCreator(username);
                roleApi.setOperator(username);
                Date date = new Date();
                roleApi.setCreateTime(date);
                roleApi.setUpdateTime(date);
                return roleApi;
            }).collect(Collectors.toList());
            wxmRoleApiService.saveBatch(entityList);
            return new Rbac32004Out(rbac32004In.getRoleId(), rbac32004In.getList());
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("给角色添加接口"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("给角色添加接口"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac32005Out service32005(Rbac32005In rbac32005In) throws BizSvcException, DbSvcException {
        try {
            if(CollectionUtils.isEmpty(rbac32005In.getList())){
                QueryWrapper wrapper=new QueryWrapper();
                wrapper.eq("role_id",Long.valueOf(rbac32005In.getRoleId()));
                wxmRolePermissionService.remove(wrapper);
                return new Rbac32005Out(rbac32005In.getRoleId(),rbac32005In.getList());
            }


            Date date = new Date();
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            String username = userDetail.getUsername();

            List<String> list = rbac32005In.getList();
            List<WxmRolePermission> listAdd = new ArrayList<>();
            List<WxmRolePermission> listDelete = new ArrayList<>();
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("role_id", Long.valueOf(rbac32005In.getRoleId()));
            List<WxmRolePermission> listDb = wxmRolePermissionService.list(queryWrapper);


            if (CollectionUtils.isEmpty(listDb)) {
                listAdd = list.stream().map(permissionId -> {
                    WxmRolePermission rolePermission = new WxmRolePermission();
                    rolePermission.setRoleId(Long.valueOf(rbac32005In.getRoleId()));
                    rolePermission.setPermissionId(Long.valueOf(permissionId));
                    rolePermission.setCreator(username);
                    rolePermission.setCreateTime(date);
                    return rolePermission;
                }).collect(Collectors.toList());
                wxmRolePermissionService.saveBatch(listAdd);
                return new Rbac32005Out(rbac32005In.getRoleId(), list);
            }
            // 传入的不匹配数据库的新增
            listAdd = list.stream().filter(permissionId -> listDb.stream().noneMatch(item -> permissionId.equals(item.getPermissionId().toString()))).map(id -> {
                WxmRolePermission rolePermission = new WxmRolePermission();
                rolePermission.setRoleId(Long.valueOf(rbac32005In.getRoleId()));
                rolePermission.setPermissionId(Long.valueOf(id));
                rolePermission.setCreator(username);
                rolePermission.setCreateTime(date);
                return rolePermission;
            }).collect(Collectors.toList());
            // 数据库的不匹配传入的，删除

            listDelete = listDb.stream().filter(item -> list.stream().noneMatch(permissionId -> permissionId.equals(item.getPermissionId().toString()))).collect(Collectors.toList());
            wxmRolePermissionService.saveBatch(listAdd);
            wxmRolePermissionService.removeBatchByIds(listDelete.stream().map(item -> item.getPermissionId()).collect(Collectors.toList()));
            return new Rbac32005Out(rbac32005In.getRoleId(), list);
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1005.toString(), BizSvcEnum.BIZ_SVC_1005.getMessage("角色的权限"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1005.toString(), BizSvcEnum.BIZ_SVC_1005.getMessage("角色的权限"), e.getMessage());
        }
    }

    @Override
    public Rbac32006Out service32006(Rbac32006In rbac32006In) throws BizSvcException, DbSvcException {
        try {
            if(CollectionUtils.isEmpty(rbac32006In.getList())){
                QueryWrapper wrapper=new QueryWrapper();
                wrapper.eq("role_id",Long.valueOf(rbac32006In.getRoleId()));
                wxmRolePermissionService.remove(wrapper);
                return new Rbac32006Out(rbac32006In.getRoleId(),rbac32006In.getList());
            }


            Date date = new Date();
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            String username = userDetail.getUsername();

            List<String> list = rbac32006In.getList();
            List<WxmRoleApi> listAdd = new ArrayList<>();
            List<WxmRoleApi> listDelete = new ArrayList<>();
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("role_id", Long.valueOf(rbac32006In.getRoleId()));
            List<WxmRoleApi> listDb = wxmRoleApiService.list(queryWrapper);


            if (CollectionUtils.isEmpty(listDb)) {
                listAdd = list.stream().map(permissionId -> {
                    WxmRoleApi roleApi = new WxmRoleApi();
                    roleApi.setRoleId(Long.valueOf(rbac32006In.getRoleId()));
                    roleApi.setApiId(Long.valueOf(permissionId));
                    roleApi.setCreator(username);
                    roleApi.setCreateTime(date);
                    return roleApi;
                }).collect(Collectors.toList());
                wxmRoleApiService.saveBatch(listAdd);
                return new Rbac32006Out(rbac32006In.getRoleId(), list);
            }
            listAdd = list.stream().filter(apiId -> listDb.stream().noneMatch(item -> apiId.equals(item.getApiId().toString()))).map(id -> {
                WxmRoleApi roleApi = new WxmRoleApi();
                roleApi.setRoleId(Long.valueOf(rbac32006In.getRoleId()));
                roleApi.setApiId(Long.valueOf(id));
                roleApi.setCreator(username);
                roleApi.setCreateTime(date);
                return roleApi;
            }).collect(Collectors.toList());
            listDelete = listDb.stream().filter(item -> list.stream().noneMatch(apiId -> apiId.equals(item.getApiId().toString()))).collect(Collectors.toList());
            wxmRoleApiService.saveBatch(listAdd);
            wxmRoleApiService.removeBatchByIds(listDelete.stream().map(item -> item.getApiId()).collect(Collectors.toList()));
            return new Rbac32006Out(rbac32006In.getRoleId(), list);
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1005.toString(), BizSvcEnum.BIZ_SVC_1005.getMessage("角色的接口"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1005.toString(), BizSvcEnum.BIZ_SVC_1005.getMessage("角色的接口"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac33001Out service33001(Rbac33001In rbac33001In) throws BizSvcException, DbSvcException {
        try {
            // 系统设定的角色编码不能变,没有配置，采用新编码
            if (Arrays.asList("R-SUPER-ADMIN", "R-ADMIN", "R-MANAGER", "R-BIZ", "R-ORDINARY").stream().anyMatch(roleCode -> roleCode.equals(rbac33001In.getRoleCode()))) {
               throw  new Exception("系统设定的角色编码不能变");
            }
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(Rbac33001In.class, WxmRole.class, true);
            WxmRole role = new WxmRole();
            instance.copy(rbac33001In, role, bc, true);


            Date date = new Date();
            role.setUpdateTime(date);
            wxmRoleService.edit(role);
            bc = instance.create(WxmRole.class, Rbac33001Out.class, true);
            Rbac33001Out out = new Rbac33001Out();
            instance.copy(role, out, bc, false);
            return out;
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1003.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("角色"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1003.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("角色"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac34001Out service34001(Rbac34001In rbac34001In) throws BizSvcException, DbSvcException {
        try {
            wxmRoleService.delete(Long.valueOf(rbac34001In.getRoleId()));
            // 角色删除、删除对应的数据 wxm_group_role   wxm_role_resource wxm_role_permission
            QueryWrapper<WxmGroupRole> wrapperGR = new QueryWrapper<>();
            wrapperGR.eq("role_id", Long.valueOf(rbac34001In.getRoleId()));
            wxmGroupRoleService.remove(wrapperGR);

            QueryWrapper<WxmRoleResource> wrapperRR = new QueryWrapper<>();
            wrapperRR.eq("role_id", Long.valueOf(rbac34001In.getRoleId()));
            wxmRoleResourceService.remove(wrapperRR);

            QueryWrapper<WxmRolePermission> wrapperRP = new QueryWrapper<>();
            wrapperRP.eq("role_id", Long.valueOf(rbac34001In.getRoleId()));
            wxmRolePermissionService.remove(wrapperRP);

            return new Rbac34001Out(rbac34001In.getRoleId());
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("角色"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("角色"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac34002Out service34002(Rbac34002In rbac34002In) throws BizSvcException, DbSvcException {
        try {
            List<String> list = rbac34002In.getList();
            if (CollectionUtils.isEmpty(list)) {
                throw new BizSvcException("传入的权限列表为空");
            }
            if (!StringUtils.hasLength(rbac34002In.getRoleId())) {
                throw new BizSvcException("传入的角色ID为空");
            }

            wxmRolePermissionService.deleteBatchByRoleIdAndPermissionIds(Long.valueOf(rbac34002In.getRoleId()), list.stream().map(permissionId -> Long.valueOf(permissionId)).collect(Collectors.toList()));
            return new Rbac34002Out(rbac34002In.getRoleId(), list);
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("移除角色中的权限"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("移除角色中的权限"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac34003Out service34003(Rbac34003In rbac34003In) throws BizSvcException, DbSvcException {
        try {

            List<String> list = rbac34003In.getList();
            if (CollectionUtils.isEmpty(list)) {
                throw new BizSvcException("传入的接口列表为空");
            }
            if (!StringUtils.hasLength(rbac34003In.getRoleId())) {
                throw new BizSvcException("传入的角色ID为空");
            }
            wxmRoleApiService.deleteBatchByRoleIdAndApiIds(Long.valueOf(rbac34003In.getRoleId()), list.stream().map(apiId -> Long.valueOf(apiId)).collect(Collectors.toList()));
            return new Rbac34003Out(rbac34003In.getRoleId(), list);
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("移除角色中的接口"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("移除角色中的接口"), e.getMessage());
        }
    }

    @Override
    public Rbac34004Out service34004(@Valid Rbac34004In rbac34004In) throws BizSvcException, DbSvcException {
        try {
            List<Long> list = rbac34004In.getList().stream().map(item -> Long.valueOf(item)).collect(Collectors.toList());
            wxmRoleService.removeByIds(list);
            // 角色删除、删除对应的数据 wxm_group_role   wxm_role_resource wxm_role_permission
            wxmGroupRoleService.deleteByRoleIds(list);

            wxmRoleResourceService.deleteByRoleIds(list);

            wxmRolePermissionService.deleteByRoleIds(list);

            return new Rbac34004Out(rbac34004In.getList());
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("角色"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("角色"), e.getMessage());
        }
    }

    @Override
    public List<Rbac41001Out> service41001(Rbac41001In rbac41001In) throws BizSvcException, DbSvcException {
        // 采用设计模式之：模板模式实现：
        Service41001 service41001 = new Service41001();
        return service41001.service(WxmDictionary.class, Rbac41001Out.class, "资源列表");
    }

    @Override
    public Rbac41002Out service41002(Rbac41002In rbac41002In) throws BizSvcException, DbSvcException {
        try {
            Rbac41002Out out = new Rbac41002Out();
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(WxmResource.class, Item41002Out.class, true);
            PageInfo<WxmResource> pageInfo = wxmResourceService.queryAllPage(rbac41002In.getCurrentPage(), rbac41002In.getPageSize());
            List<WxmResource> listSource = pageInfo.getList();
            if (!CollectionUtils.isEmpty(listSource)) {
                List<Item41002Out> list = listSource.stream().map(item -> {
                    Item41002Out item41002Out = new Item41002Out();
                    instance.copy(item, item41002Out, bc, false);
                    return item41002Out;
                }).collect(Collectors.toList());
                out.setList(list);
            }
            out.setTotal(pageInfo.getTotal());
            return out;
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("资源列表"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("资源列表"), e.getMessage());
        }
    }

    @Override
    public Rbac41003Out service41003(Rbac41003In rbac41003In) throws BizSvcException, DbSvcException {
        try {
            List<TreeNode> listTreeNode = wxmResourceService.queryAll().stream().map(resource -> {
                TreeNode treeNode = new TreeNode();
                treeNode.setNodeId(String.valueOf(resource.getResourceId()));
                treeNode.setTitle(resource.getResourceName());
                treeNode.setParentId(resource.getParentId() == null ? "0" : String.valueOf(resource.getParentId()));
                treeNode.setIcon(resource.getResourceIcon());
                treeNode.setUrl(resource.getResourceUrl());
                treeNode.setIsLeaf("2".equals(resource.getResourceType()));
                return treeNode;
            }).collect(Collectors.toList());
            List<TreeNode> list = TreeUtils.buildTree(listTreeNode, "0");
            return new Rbac41003Out(list);
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("资源树"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("资源树"), e.getMessage());
        }
    }

    @Override
    public Rbac41004Out service41004(Rbac41004In rbac41004In) throws BizSvcException, DbSvcException {
        try {
            Rbac41004Out out = new Rbac41004Out();
            WxmResource resource = wxmResourceService.queryById(Long.valueOf(rbac41004In.getResourceId()));
            if (resource == null) {
                return out;
            }
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(WxmResource.class, Rbac41004Out.class, true);
            instance.copy(resource, out, bc, false);
            WxmResource parent = wxmResourceService.queryById(resource.getParentId());
            out.setParentName(parent == null || !StringUtils.hasLength(parent.getResourceName()) ? "根目录" : parent.getResourceName());
            return out;
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("资源"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("资源"), e.getMessage());
        }
    }

    @Override
    public Rbac41005Out service41005(Rbac41005In rbac41005In) throws BizSvcException, DbSvcException {
        try {
             Long resourceId=StringUtils.hasLength(rbac41005In.getResourceId())? Long.valueOf(rbac41005In.getResourceId()):null;
            List<TreeNode> listTreeNode = wxmResourceService.queryByCon(resourceId,rbac41005In.getResourceName(),rbac41005In.getResourceUrl(),rbac41005In.getResourceType()).stream().map(resource -> {
//            List<TreeNode> listTreeNode = wxmResourceService.queryAll().stream().map(resource -> {
                TreeNode treeNode = new TreeNode();
                treeNode.setNodeId(String.valueOf(resource.getResourceId()));
                treeNode.setTitle(resource.getResourceName());
                treeNode.setParentId(resource.getParentId() == null ? "0" : String.valueOf(resource.getParentId()));
                treeNode.setIcon(resource.getResourceIcon());
                treeNode.setUrl(resource.getResourceUrl());
                treeNode.setIsLeaf("2".equals(resource.getResourceType()));
                return treeNode;
            }).collect(Collectors.toList());
            List<TreeNode> list = TreeUtils.buildTree(listTreeNode, "0");

            List<String> listResourceId = new ArrayList<>();
            if(StringUtils.hasLength(rbac41005In.getRoleId())){
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("role_id", Long.valueOf(rbac41005In.getRoleId()));
                List<WxmRoleResource> listRR = wxmRoleResourceService.list(queryWrapper);
                if (!CollectionUtils.isEmpty(listRR)) {
                    listResourceId = listRR.stream().map(item -> String.valueOf(item.getResourceId())).collect(Collectors.toList());
                }
            }

            return new Rbac41005Out(list, listResourceId);
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("资源树"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("资源树"), e.getMessage());
        }
    }

    @Override
    public Rbac41006Out service41006(@Valid Rbac41006In rbac41006In) throws BizSvcException, DbSvcException {
        try {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            ;
            String username = userDetail.getUsername();
            List<WxmResource> listDb = wxmResourceService.queryByUsername(username);
            if (CollectionUtils.isEmpty(listDb)) {
                return new Rbac41006Out(new ArrayList<>());
            }
            List<TreeNode> listTreeNode = listDb.stream().map(resource -> {
                TreeNode treeNode = new TreeNode();
                treeNode.setNodeId(String.valueOf(resource.getResourceId()));
                treeNode.setTitle(resource.getResourceName());
                treeNode.setParentId(resource.getParentId() == null ? "0" : String.valueOf(resource.getParentId()));
                treeNode.setIcon(resource.getResourceIcon());
                treeNode.setUrl(resource.getResourceUrl());
                treeNode.setIsLeaf("2".equals(resource.getResourceType()));
                return treeNode;
            }).collect(Collectors.toList());
            List<TreeNode> list = TreeUtils.buildTree(listTreeNode, "0");
            return new Rbac41006Out(list);
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("资源树"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("资源树"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac42001Out service42001(Rbac42001In rbac42001In) throws BizSvcException, DbSvcException {
        try {
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(Rbac42001In.class, WxmResource.class, true);
            WxmResource resource = new WxmResource();
            instance.copy(rbac42001In, resource, bc, true);
            Date date = new Date();
            if (!StringUtils.hasLength(rbac42001In.getParentId())) {
                resource.setParentId(0L);
            }
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            ;
            String username = userDetail.getUsername();
            resource.setResourceCode("RS-" + UUID.randomUUID().toString().replaceAll("-", ""));
            resource.setCreator(username);
            resource.setCreateTime(date);
            resource.setUpdateTime(date);
            wxmResourceService.add(resource);
            bc = instance.create(WxmResource.class, Rbac42001Out.class, true);
            Rbac42001Out out = new Rbac42001Out();
            instance.copy(resource, out, bc, false);
            return out;
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("资源"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("资源"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac43001Out service43001(Rbac43001In rbac43001In) throws BizSvcException, DbSvcException {
        try {
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(Rbac43001In.class, WxmResource.class, true);
            WxmResource resource = new WxmResource();
            instance.copy(rbac43001In, resource, bc, true);
            Date date = new Date();
            resource.setUpdateTime(date);
            wxmResourceService.edit(resource);
            bc = instance.create(WxmResource.class, Rbac43001Out.class, true);
            Rbac43001Out out = new Rbac43001Out();
            instance.copy(resource, out, bc, false);
            return out;
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1003.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("资源"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1003.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("资源"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac44001Out service44001(Rbac44001In rbac44001In) throws BizSvcException, DbSvcException {
        try {
            wxmResourceService.delete(Long.valueOf(rbac44001In.getResourceId()));
            // 资源删除的时候，需要深处对应的关联表数据
            // wxm_role_resource 、wxm_permission、wxm_role_permission
            QueryWrapper<WxmRoleResource> wrapperRR = new QueryWrapper<>();
            wrapperRR.eq("resource_id", Long.valueOf(rbac44001In.getResourceId()));
            wxmRoleResourceService.remove(wrapperRR);
            QueryWrapper<WxmPermission> wrapperP = new QueryWrapper<>();
            wrapperP.eq("resource_id", Long.valueOf(rbac44001In.getResourceId()));


            return new Rbac44001Out(rbac44001In.getResourceId());
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("资源"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("资源"), e.getMessage());
        }
    }

    @Override
    public List<Rbac51001Out> service51001(Rbac51001In rbac51001In) throws BizSvcException, DbSvcException {
        Service51001 service51001 = new Service51001();
        return service51001.service(WxmPermission.class, Rbac51001Out.class, "权限列表");
    }

    @Override
    public Rbac51002Out service51002(Rbac51002In rbac51002In) throws BizSvcException, DbSvcException {
        try {
            Rbac51002Out out = new Rbac51002Out();

            PageInfo<WxmPermission> pageInfo = wxmPermissionService.queryPageByCon(rbac51002In);

            List<WxmPermission> listSource = pageInfo.getList();
            if (!CollectionUtils.isEmpty(listSource)) {
                BeanUtils instance = BeanUtils.getInstance();
                BeanCopier bc = instance.create(WxmPermission.class, Item51002Out.class, true);
                List<Item51002Out> list = listSource.stream().map(source -> {
                    Item51002Out item51002Out = new Item51002Out();
                    instance.copy(source, item51002Out, bc, false);
                    return item51002Out;
                }).collect(Collectors.toList());
                out.setList(list);
            }
            out.setTotal(pageInfo.getTotal());
            return out;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("权限列表"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("权限列表"), e.getMessage());
        }
    }

    @Override
    public Rbac51003Out service51003(Rbac51003In rbac51003In) throws BizSvcException, DbSvcException {
        try {
            Rbac51003Out out = new Rbac51003Out();

            PageInfo<WxmPermission> pageInfo = wxmPermissionService.queryPageByConNonRole(rbac51003In);


            List<WxmPermission> listSource = pageInfo.getList();
            if (!CollectionUtils.isEmpty(listSource)) {
                BeanUtils instance = BeanUtils.getInstance();
                BeanCopier bc = instance.create(WxmPermission.class, Item51003Out.class, true);
                List<Item51003Out> list = listSource.stream().map(source -> {
                    Item51003Out item51003Out = new Item51003Out();
                    instance.copy(source, item51003Out, bc, false);
                    return item51003Out;
                }).collect(Collectors.toList());
                out.setList(list);
            }
            out.setTotal(pageInfo.getTotal());
            return out;
        } catch (DbSvcException e){
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("权限列表"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("权限列表"), e.getMessage());
        }
    }

    @Override
    public Rbac51004Out service51004(@Valid Rbac51004In rbac51004In) throws BizSvcException, DbSvcException {
        try {
            Rbac51004Out out = new Rbac51004Out();
            UserDetails userDetails= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Collection<? extends GrantedAuthority> authorities =userDetails.getAuthorities();
            if(!CollectionUtils.isEmpty(authorities)){
                List<String>roleCodeList=authorities.stream().map(item->item.getAuthority()).collect(Collectors.toList());
                QueryWrapper queryWrapper=new QueryWrapper();
                queryWrapper.in("role_code",roleCodeList);
                List<WxmRole>listRole=wxmRoleService.list(queryWrapper);
                if(!CollectionUtils.isEmpty(listRole)){
                    queryWrapper=new QueryWrapper();
                    queryWrapper.in("role_id",listRole.stream().map(item->item.getRoleId()).collect(Collectors.toList()));
                   List<WxmRolePermission>listRolePermission= wxmRolePermissionService.list(queryWrapper);
                   if(!CollectionUtils.isEmpty(listRolePermission)){
                       queryWrapper=new QueryWrapper();
                       queryWrapper.in("permission_id",listRolePermission.stream().map(item->item.getPermissionId()).collect(Collectors.toList()));
                       List<WxmPermission>listPermission=wxmPermissionService.list(queryWrapper);
                       if(!CollectionUtils.isEmpty(listPermission)){
                           out.setList(listPermission.stream().map(item->item.getPermissionCode()).collect(Collectors.toList()));
                       }
                   }
                }
            }

            return out;
        }  catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("当前登录用户的权限列表"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("当前登录用户的权限列表"), e.getMessage());
        }
    }

    @Override
    public Rbac51005Out service51005(Rbac51005In rbac51005In) throws BizSvcException, DbSvcException {
        try {
             Rbac51005Out out=new Rbac51005Out();
             PageInfo<WxmPermission> pageInfo=wxmPermissionService.queryPageByConAboutRole(rbac51005In);
             List<WxmPermission>listSource=pageInfo.getList();
             if(!CollectionUtils.isEmpty(listSource)){
                BeanUtils instance =  BeanUtils.getInstance();
                BeanCopier bc=instance.create(WxmPermission.class,Item51005Out.class,true);
                List<Item51005Out> list= listSource.stream().map(item->{
                     Item51005Out item51005Out=new Item51005Out();
                     instance.copy(item,item51005Out,bc,false);
                     return item51005Out;
                 }).collect(Collectors.toList());
                out.setList(list);
             }

             out.setTotal(pageInfo.getTotal());
             return out;
        } catch (DbSvcException e){
            throw e;
        }  catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("权限列表"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("权限列表"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac52001Out service52001(Rbac52001In rbac52001In) throws BizSvcException, DbSvcException {
        try {
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(Rbac52001In.class, WxmPermission.class, true);
            WxmPermission permission = new WxmPermission();
            instance.copy(rbac52001In, permission, bc, true);
            // 所以根据角色查询权限时，根据资源分类
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            String username = userDetail.getUsername();
            permission.setCreator(username);
            permission.setOperator(username);
            Date date = new Date();
            permission.setCreateTime(date);
            permission.setUpdateTime(date);
            wxmPermissionService.save(permission);
            bc = instance.create(WxmPermission.class, Rbac52001Out.class, true);
            Rbac52001Out out = new Rbac52001Out();
            instance.copy(permission, out, bc, false);
            return out;
        }  catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("权限"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("权限"), e.getMessage());
        }
    }


    @DSTransactional
    @Override
    public Rbac53001Out service53001(Rbac53001In rbac53001In) throws BizSvcException, DbSvcException {
        try {
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(Rbac53001In.class, WxmPermission.class, true);
            WxmPermission permission = new WxmPermission();
            instance.copy(rbac53001In, permission, bc, true);
            permission.setUpdateTime(new Date());
            UserDetails userDetail = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            permission.setOperator(userDetail.getUsername());
            wxmPermissionService.updateById(permission);
            bc = instance.create(WxmPermission.class, Rbac53001Out.class, true);
            Rbac53001Out out = new Rbac53001Out();
            instance.copy(permission, out, bc, false);
            return out;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1003.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("权限"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1003.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("权限"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac54001Out service54001(Rbac54001In rbac54001In) throws BizSvcException, DbSvcException {
        try {
            wxmPermissionService.removeById(Long.valueOf(rbac54001In.getPermissionId()));
            // 权限删除时，删除对应的数据 wxm_role_permission
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("permission_id", Long.valueOf(rbac54001In.getPermissionId()));
            wxmRolePermissionService.remove(queryWrapper);
            return new Rbac54001Out(rbac54001In.getPermissionId());
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("权限"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("权限"), e.getMessage());
        }
    }

    @Override
    public Rbac54002Out service54002(Rbac54002In rbac54002In) throws BizSvcException, DbSvcException {
        try {
            List<Long> list = rbac54002In.getList().stream().map(item -> Long.valueOf(item)).collect(Collectors.toList());
            wxmPermissionService.removeByIds(list);
            // 权限删除时，删除对应的数据 wxm_role_permission
            wxmRolePermissionService.deleteByPermissionIds(list);
            return new Rbac54002Out(rbac54002In.getList());
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("权限"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("权限"), e.getMessage());
        }
    }


    @Override
    public List<Rbac61001Out> service61001(Rbac61001In rbac61001In) throws BizSvcException, DbSvcException {
 /*       try {
            List<WxmDictionary>list=wxmDictionaryService.queryAll();
            // 单例模式
            BeanUtils instance=BeanUtils.getInstance();
            BeanCopier beanCopier = instance.create(WxmDictionary.class,Rbac51001Out.class,true);
            Rbac51001Out rbac51001Out = new Rbac51001Out();
            return list.stream().map(source->{
                 instance.copy(source,rbac51001Out,beanCopier);
                 return rbac51001Out;
            }).collect(Collectors.toList());
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("字典"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("字典"));
        }*/

        // 采用设计模式之：模板模式实现：
        Service61001 service61001 = new Service61001();
        return service61001.service(WxmDictionary.class, Rbac61001Out.class, "字典列表");
    }

    @Override
    public List<Rbac61002Out> service61002(Rbac61002In rbac61002In) throws BizSvcException, DbSvcException {
        try {
            List<WxmDictionary> list = wxmDictionaryService.queryList(rbac61002In.getTypeList());
            // 单例模式
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier beanCopier = instance.create(WxmDictionary.class, Rbac61001Out.class, true);

            return list.stream().map(source -> {
                Rbac61002Out rbac61002Out = new Rbac61002Out();
                instance.copy(source, rbac61002Out, beanCopier);
                return rbac61002Out;
            }).collect(Collectors.toList());
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("字典列表"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("字典列表"), e.getMessage());
        }
    }

    @Override
    public Rbac61003Out service61003( Rbac61003In rbac61003In) throws BizSvcException, DbSvcException {
        Service61003 service61003 = new Service61003();
        return service61003.service(rbac61003In, WxmDictionary.class, Item61003Out.class, "字典列表");
    }

    @Override
    public Rbac62001Out service62001(Rbac62001In rbac62001In) throws BizSvcException, DbSvcException {
        try {
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(Rbac62001In.class, WxmDictionary.class, true);
            WxmDictionary wxmDictionary = new WxmDictionary();
            instance.copy(rbac62001In, wxmDictionary, bc, true);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            String username = userDetail.getUsername();
            wxmDictionaryService.save(wxmDictionary);
            bc = instance.create(WxmDictionary.class, Rbac62001Out.class, true);
            Rbac62001Out out = new Rbac62001Out();
            instance.copy(wxmDictionary, out, bc, false);
            return out;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("字典"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("字典"), e.getMessage());
        }
    }

    @Override
    public Rbac63001Out service63001(Rbac63001In rbac63001In) throws BizSvcException, DbSvcException {
        try {
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(Rbac63001In.class, WxmDictionary.class, true);
            WxmDictionary wxmDictionary = new WxmDictionary();
            instance.copy(rbac63001In, wxmDictionary, bc, true);
            wxmDictionaryService.updateById(wxmDictionary);
            bc = instance.create(WxmDictionary.class, Rbac63001Out.class, true);
            Rbac63001Out out = new Rbac63001Out();
            instance.copy(wxmDictionary, out, bc, false);
            return out;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1003.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("字典"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1003.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("字典"), e.getMessage());
        }
    }

    @Override
    public Rbac64001Out service64001(Rbac64001In rbac64001In) throws BizSvcException, DbSvcException {
        try {
            wxmDictionaryService.removeById(Long.valueOf(rbac64001In.getDictId()));
            return new Rbac64001Out(rbac64001In.getDictId());
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("字典"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("字典"), e.getMessage());
        }
    }

    @Override
    public Rbac64002Out service64002(Rbac64002In rbac64002In) throws BizSvcException, DbSvcException {
        try {
            List<Long> list = rbac64002In.getList().stream().map(item -> Long.valueOf(item)).collect(Collectors.toList());
            wxmDictionaryService.removeByIds(list);
            return new Rbac64002Out(rbac64002In.getList());
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("字典"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("字典"), e.getMessage());
        }
    }

    @Override
    public Rbac71002Out service71002(Rbac71002In rbac71002In) throws BizSvcException, DbSvcException {
        Service71002 service71002 = new Service71002();
        return service71002.service(rbac71002In, WxmApi.class, Item71002Out.class, "接口列表");
    }

    @Override
    public Rbac71003Out service71003(Rbac71003In rbac71003In) throws BizSvcException, DbSvcException {
        Service71003 service71003 = new Service71003();
        return service71003.service(rbac71003In, WxmApi.class, Item71003Out.class, "接口列表");
    }

    @Override
    public Rbac71005Out service71005(@Valid Rbac71005In rbac71005In) throws BizSvcException, DbSvcException {
        Service71005 service71005=new Service71005();
        return service71005.service(rbac71005In,WxmApi.class,Item71005Out.class,"接口列表");
    }

    @DSTransactional
    @Override
    public Rbac72001Out service72001(Rbac72001In rbac72001In) throws BizSvcException, DbSvcException {
        try {
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(Rbac72001In.class, WxmApi.class, true);
            WxmApi wxmApi = new WxmApi();
            instance.copy(rbac72001In, wxmApi, bc, true);
            wxmApi.setApiCode("API-" + UUID.randomUUID().toString().replaceAll("-", ""));
            Date date = new Date();
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            ;
            String username = userDetail.getUsername();
            wxmApi.setOperator(username);
            wxmApi.setCreator(username);
            wxmApi.setCreateTime(date);
            wxmApi.setUpdateTime(date);
            wxmApiService.save(wxmApi);
            bc = instance.create(WxmApi.class, Rbac72001Out.class, true);
            Rbac72001Out out = new Rbac72001Out();
            instance.copy(wxmApi, out, bc, false);
            return out;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("接口"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("接口"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac73001Out service73001(Rbac73001In rbac73001In) throws BizSvcException, DbSvcException {
        try {
            BeanUtils instance = BeanUtils.getInstance();
            BeanCopier bc = instance.create(Rbac73001In.class, WxmApi.class, true);
            WxmApi wxmApi = new WxmApi();
            instance.copy(rbac73001In, wxmApi, bc, true);
            Date date = new Date();
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            ;
            String username = userDetail.getUsername();
            wxmApi.setOperator(username);
            wxmApi.setUpdateTime(date);
            wxmApiService.updateById(wxmApi);
            bc = instance.create(WxmApi.class, Rbac73001Out.class, true);
            Rbac73001Out out = new Rbac73001Out();
            instance.copy(wxmApi, out, bc, false);
            return out;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1003.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("接口"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1003.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("接口"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Rbac74001Out service74001(Rbac74001In rbac74001In) throws BizSvcException, DbSvcException {
        try {
            wxmApiService.removeById(Long.valueOf(rbac74001In.getApiId()));
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("api_id", Long.valueOf(rbac74001In.getApiId()));
            wxmRoleApiService.remove(queryWrapper);
            return new Rbac74001Out(rbac74001In.getApiId());
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("接口"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("接口"), e.getMessage());
        }
    }

    @Override
    public Rbac74002Out service74002(Rbac74002In rbac74002In) throws BizSvcException, DbSvcException {
        try {
            List<Long> list = rbac74002In.getList().stream().map(item -> Long.valueOf(item)).collect(Collectors.toList());
            wxmApiService.removeByIds(list);
            wxmRoleApiService.deleteByApiIds(list);
            return new Rbac74002Out(rbac74002In.getList());
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("接口"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("接口"), e.getMessage());
        }
    }


    /**
     * ==========================================
     * 内部内，实现模板模式（设计模式之一）
     * =========================================
     */
    /**
     * 用户列表查询
     *
     * @param
     */
    class Service11001 extends Template {

        @Override
        public List<?> queryList() throws DbSvcException {

            return wxmUserService.queryAll();
        }
    }

    class Service11002 extends TemplateWithParams<Rbac11002Out, Rbac11002In, Item11002Out> {

        @Override
        public PageInfo<?> queryPageInfo(Rbac11002In rbac11002In) throws DbSvcException {
            return wxmUserService.queryPageByCon(rbac11002In);
        }

        @Override
        public Rbac11002Out initOut(List<Item11002Out> list, long total) {
            return new Rbac11002Out(total, list);
        }
    }

    class Service11003 extends TemplateWithParams<Rbac11003Out, Rbac11003In, Item11003Out> {

        @Override
        public PageInfo<?> queryPageInfo(Rbac11003In rbac11003In) throws DbSvcException {
            return wxmUserService.queryPageByConNonGroup(rbac11003In);
        }

        @Override
        public Rbac11003Out initOut(List<Item11003Out> list, long total) {
            return new Rbac11003Out(total, list);
        }
    }

    /**
     * 用户组列表查询
     *
     * @param
     */
    class Service21001 extends Template {
        @Override
        public List<?> queryList() throws DbSvcException {
            return wxmGroupService.queryAll();
        }
    }

    class Service21002 extends TemplateWithParams<Rbac21002Out, Rbac21002In, Item21002Out> {

        @Override
        public PageInfo<?> queryPageInfo(Rbac21002In rbac21002In) throws DbSvcException {
            return wxmGroupService.queryPageByCon(rbac21002In);
        }

        @Override
        public Rbac21002Out initOut(List<Item21002Out> list, long total) {
            return new Rbac21002Out(total, list);
        }
    }

    /**
     * 用户组列表查询
     *
     * @param <Rbac31001Out>
     */
    class Service31001<Rbac31001Out> extends Template {
        @Override
        public List<?> queryList() throws DbSvcException {
            return wxmRoleService.queryAll();
        }
    }

    /**
     * 用户组列表查询
     *
     * @param <Rbac41001Out>
     */
    class Service41001<Rbac41001Out> extends Template {
        @Override
        public List<?> queryList() throws DbSvcException {
            return wxmResourceService.queryAll();
        }
    }

    /**
     * 权限列表查询
     *
     * @param <Rbac51001Out>
     */
    class Service51001<Rbac51001Out> extends Template {

        @Override
        public List<?> queryList() throws DbSvcException {
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("permission_status","1");
            return wxmPermissionService.list(queryWrapper);
        }
    }

    /**
     * 字典列表查询
     *
     * @param <Rbac610001Out>
     */
    class Service61001<Rbac610001Out> extends Template {
        @Override
        public List<?> queryList() throws DbSvcException {
            return wxmDictionaryService.queryAll();
        }
    }

    class Service61003 extends  TemplateWithParams<Rbac61003Out,Rbac61003In,Item61003Out>{

        @Override
        public PageInfo<?> queryPageInfo(Rbac61003In rbac61003In) throws DbSvcException {
            return wxmDictionaryService.queryPageByCon(rbac61003In);
        }

        @Override
        public Rbac61003Out initOut(List<Item61003Out> list, long total) {
            return new Rbac61003Out(total,list);
        }
    }

    class Service71002 extends TemplateWithParams<Rbac71002Out, Rbac71002In, Item71002Out> {

        @Override
        public PageInfo<?> queryPageInfo(Rbac71002In rbac71002In) throws DbSvcException {
            return wxmApiService.queryPageByCon(rbac71002In);
        }

        @Override
        public Rbac71002Out initOut(List<Item71002Out> list, long total) {
            return new Rbac71002Out(total, list);
        }
    }

    class Service71005 extends TemplateWithParams<Rbac71005Out, Rbac71005In, Item71005Out> {

        @Override
        public PageInfo<?> queryPageInfo(Rbac71005In rbac71005In) throws DbSvcException {
            return wxmApiService.queryPageByConAboutRole(rbac71005In);
        }

        @Override
        public Rbac71005Out initOut(List<Item71005Out> list, long total) {
            return new Rbac71005Out(total, list);
        }
    }

    class Service71003 extends TemplateWithParams<Rbac71003Out, Rbac71003In, Item71003Out> {

        @Override
        public PageInfo<?> queryPageInfo(Rbac71003In rbac71003In) throws DbSvcException {
            return wxmApiService.queryPageByConNonRole(rbac71003In);
        }

        @Override
        public Rbac71003Out initOut(List<Item71003Out> list, long total) {
            return new Rbac71003Out(total, list);
        }
    }
}
