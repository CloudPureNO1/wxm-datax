package com.wxm.service.db.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.pagehelper.PageInfo;
import com.wxm.base.dto.rbac.in.Rbac71002In;
import com.wxm.base.dto.rbac.in.Rbac71003In;
import com.wxm.base.dto.rbac.in.Rbac71005In;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.biz.WxmApi;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 接口资源api 服务类
 * </p>
 *
 * @author 王森明
 * @since 2022-06-27
 */
@DS("biz")
public interface IWxmApiService extends IService<WxmApi> {
    /**
     * 条件查询接口，如果roleId 不为空，则查询roleId的接口
     * @param rbac71002In
     * @return
     * @throws DbSvcException
     */
    PageInfo<WxmApi> queryPageByCon(Rbac71002In rbac71002In) throws DbSvcException;
    /**
     * 条件查询接口，如果roleId 不为空，则查询roleId的接口
     * @param rbac71003In
     * @return
     * @throws DbSvcException
     */
    PageInfo<WxmApi> queryPageByConNonRole(Rbac71003In rbac71003In) throws DbSvcException;

    /**
     * 更加用户名查询接口
     * @param username
     * @return
     */
    List<WxmApi>queryByUsername(String username) throws DbSvcException;

    /**
     * 查询所有接口信息，包含是否授权给角色，角色为空时，是否授权全部为否0
     * @param rbac71005In
     * @return
     * @throws DbSvcException
     */
    PageInfo<WxmApi> queryPageByConAboutRole(Rbac71005In rbac71005In) throws DbSvcException;
}
