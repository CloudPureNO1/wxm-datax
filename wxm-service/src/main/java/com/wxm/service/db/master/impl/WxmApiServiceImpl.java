package com.wxm.service.db.master.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxm.base.dto.rbac.in.Rbac71002In;
import com.wxm.base.dto.rbac.in.Rbac71003In;
import com.wxm.base.dto.rbac.in.Rbac71005In;
import com.wxm.base.enums.DbSvcEnum;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.master.WxmApi;
import com.wxm.druid.mapper.master.WxmApiMapper;
import com.wxm.service.db.master.IWxmApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 接口资源api 服务实现类
 * </p>
 *
 * @author 王森明
 * @since 2022-06-27
 */
@Slf4j
@Service
public class WxmApiServiceImpl extends ServiceImpl<WxmApiMapper, WxmApi> implements IWxmApiService {

    @Override
    public PageInfo<WxmApi> queryPageByCon(Rbac71002In rbac71002In) throws DbSvcException {
        try {
            PageHelper.startPage(rbac71002In.getCurrentPage(), rbac71002In.getPageSize());
            Long roleId = null;
            if (StringUtils.hasLength(rbac71002In.getRoleId())) {
                roleId = Long.valueOf(rbac71002In.getRoleId());
                ;
            }
            Long apiId = null;
            if (StringUtils.hasLength(rbac71002In.getApiId())) {
                apiId = Long.valueOf(rbac71002In.getApiId());
            }
            List<WxmApi> list = baseMapper.selectByCon(apiId, rbac71002In.getApiTitle(), rbac71002In.getApiCode(), rbac71002In.getApiUrl(), rbac71002In.getCreator(),rbac71002In.getApiStatus(),roleId);
            return new PageInfo<>(list);
        } catch (Exception e) {
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("接口列表"), e.getMessage(), e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("接口列表"), e.getMessage());
        }
    }

    @Override
    public PageInfo<WxmApi> queryPageByConNonRole(Rbac71003In rbac71003In) throws DbSvcException {
        try {
            PageHelper.startPage(rbac71003In.getCurrentPage(), rbac71003In.getPageSize());
            Long roleId = null;
            if (StringUtils.hasLength(rbac71003In.getRoleId())) {
                roleId = Long.valueOf(rbac71003In.getRoleId());
                ;
            }
            Long apiId = null;
            if (StringUtils.hasLength(rbac71003In.getApiId())) {
                apiId = Long.valueOf(rbac71003In.getApiId());
            }
            List<WxmApi> list = baseMapper.selectByConNonRole(apiId, rbac71003In.getApiTitle(), rbac71003In.getApiCode(), rbac71003In.getApiUrl(), rbac71003In.getCreator(),rbac71003In.getApiStatus(), roleId);
            return new PageInfo<>(list);
        } catch (Exception e) {
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("接口列表"), e.getMessage(), e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("接口列表"), e.getMessage());
        }
    }

    @Override
    public List<WxmApi> queryByUsername(String username) throws DbSvcException {
        try {
            return baseMapper.selectByUsername(username);
        } catch (Exception e) {
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("接口列表"), e.getMessage(), e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("接口列表"), e.getMessage());
        }
    }

    @Override
    public PageInfo<WxmApi> queryPageByConAboutRole(Rbac71005In rbac71005In) throws DbSvcException {
        try {
            PageHelper.startPage(rbac71005In.getCurrentPage(), rbac71005In.getPageSize());
            Long roleId = StringUtils.hasLength(rbac71005In.getRoleId())?Long.valueOf(rbac71005In.getRoleId()):null;
            Long apiId = StringUtils.hasLength(rbac71005In.getApiId())?Long.valueOf(rbac71005In.getApiId()):null;

            List<WxmApi> list = baseMapper.selectByConAboutRole(apiId, rbac71005In.getApiTitle(), roleId);
            return new PageInfo<>(list);
        } catch (Exception e) {
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("接口列表"), e.getMessage(), e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("接口列表"), e.getMessage());
        }
    }
}
