package com.wxm.service.db.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.biz.WxmRoleApi;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色api 关联表 服务类
 * </p>
 *
 * @author 王森明
 * @since 2022-06-27
 */
@DS("biz")
public interface IWxmRoleApiService extends IService<WxmRoleApi> {
    /**
     * 根据角色Id 和 接口id删除
     * @param roleId
     * @param list
     * @return
     */
    int deleteBatchByRoleIdAndApiIds(Long roleId,List<Long> list) throws DbSvcException;

    /**
     * 根据接口ID列表删除
     * @param list
     * @return
     * @throws DbSvcException
     */
    int deleteByApiIds(List<Long>list) throws DbSvcException;
}
