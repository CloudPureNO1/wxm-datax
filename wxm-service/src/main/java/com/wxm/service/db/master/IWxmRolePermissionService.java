package com.wxm.service.db.master;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.master.WxmRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色-权限关联表 服务类
 * </p>
 *
 * @author 王森明
 * @since 2022-06-17
 */
public interface IWxmRolePermissionService extends IService<WxmRolePermission> {
    /**
     * 根据角色Id,权限Id列表删除
     * @param roleId
     * @param list
     * @return
     * @throws DbSvcException
     */
    int deleteBatchByRoleIdAndPermissionIds(@Param("roleId")Long roleId, @Param("list") List<Long> list) throws DbSvcException;

    /**
     * 根据角色Id列表批量删除
     * @param list
     * @return
     * @throws DbSvcException
     */
    int deleteByRoleIds(List<Long>list) throws DbSvcException;

    /**
     * 根据权限ID列表删除
     * @param list
     * @return
     * @throws DbSvcException
     */
    int deleteByPermissionIds(List<Long>list) throws DbSvcException;
}
