package com.wxm.service.db.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.wxm.base.dto.rbac.in.Rbac31002In;
import com.wxm.base.dto.rbac.in.Rbac31003In;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.biz.WxmRole;
import com.wxm.service.db.IDbService;

import java.util.List;
/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/6 10:44
 * @since 1.0.0
 */
@DS("biz")
public interface IWxmRoleService extends IDbService, IService<WxmRole> {
    List<WxmRole>queryAll() throws DbSvcException;
    PageInfo<WxmRole>queryAllPage(int currentPage, int pageSize) throws DbSvcException;
    PageInfo<WxmRole> queryPageByCon(Rbac31002In rbac11002In) throws DbSvcException;
    PageInfo<WxmRole> queryPageByConNonGroup(Rbac31003In rbac11003In) throws DbSvcException;
    int add(WxmRole role) throws DbSvcException;
    int edit(WxmRole role) throws DbSvcException;
    int delete(Long roleId) throws DbSvcException;

    /**
     * 更加资源url 查询对应的角色，作为springsecurity 权限验证
     * @param resourceUrl
     * @return
     * @throws DbSvcException
     */
    List<WxmRole>queryByResourceUrl(String resourceUrl) throws DbSvcException;

    /**
     * 根据api接口地址 查询对应的角色，作为springsecurity 权限验证
     * @param apiUrl
     * @return
     * @throws DbSvcException
     */
    List<WxmRole>queryByApiUrl(String apiUrl) throws DbSvcException;
    /**
     * spring security 校验使用
     * @param username
     * @return
     */
    List<WxmRole>findRolesByUsername(String username) throws DbSvcException;

}
