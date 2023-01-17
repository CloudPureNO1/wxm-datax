package com.wxm.service.db.master;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.master.WxmResource;
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
public interface IWxmResourceService extends IDbService, IService<WxmResource> {
    List<WxmResource>queryAll() throws DbSvcException;

    /**
     * 条件查询
     * @param resourceId
     * @param resourceName
     * @param resourceUrl
     * @param resourceType
     * @return
     * @throws DbSvcException
     */
    List<WxmResource>queryByCon(Long resourceId,String resourceName,String resourceUrl,String resourceType) throws DbSvcException;

    /**
     * 更加用户名查询有效的资源列表
     * @param username
     * @return
     * @throws DbSvcException
     */
    List<WxmResource>queryByUsername(String username) throws DbSvcException;
    /**
     * 根据角色ID查询对应的资源
     * @param roleId
     * @return
     * @throws DbSvcException
     */
    List<WxmResource> queryByRoleId(Long roleId) throws DbSvcException;
    PageInfo<WxmResource> queryAllPage(int currentPage, int pageSize) throws DbSvcException;
    WxmResource queryById(Long id) throws DbSvcException;
    int add(WxmResource resource) throws DbSvcException;
    int edit(WxmResource resource) throws DbSvcException;
    int delete(Long resourceId) throws DbSvcException;
}
