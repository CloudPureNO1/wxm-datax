package com.wxm.service.db.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.wxm.base.dto.rbac.in.Rbac51002In;
import com.wxm.base.dto.rbac.in.Rbac51003In;
import com.wxm.base.dto.rbac.in.Rbac51005In;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.biz.WxmPermission;

/**
 * <p>
 * 资源的权限表（资源中的按钮和其他功能） 服务类
 * </p>
 *
 * @author 王森明
 * @since 2022-06-17
 */
@DS("biz")
public interface IWxmPermissionService extends IService<WxmPermission> {
    PageInfo queryAllPage(int currentPage, int pageSize) throws DbSvcException;
    PageInfo queryPageByCon(Rbac51002In rbac51002In) throws DbSvcException;
    PageInfo queryPageByConNonRole(Rbac51003In rbac51003In) throws DbSvcException;

    /**
     * 查询所有权限信息，包含是否授权给角色，角色为空时，是否授权全部为否0     rbac51005
     * @param rbac51005In
     * @return
     * @throws DbSvcException
     */
    PageInfo queryPageByConAboutRole(Rbac51005In rbac51005In) throws DbSvcException;
}
