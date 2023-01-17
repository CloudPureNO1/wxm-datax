package com.wxm.service.db.master;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.master.WxmGroupRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户组-角色关联表 服务类
 * </p>
 *
 * @author 王森明
 * @since 2022-06-17
 */
public interface IWxmGroupRoleService extends IService<WxmGroupRole> {
    /**
     * 根据用户组Id,和角色Id列表，删除
     * @param groupId
     * @param list
     * @return
     * @throws DbSvcException
     */
    int deleteByGroupIdAndRoleIds(Long groupId, List<Long> list) throws DbSvcException;

    /**
     * 根据用户组Id列表批量删除
     * @param list
     * @return
     * @throws DbSvcException
     */
    int deleteByGroupIds(List<Long>list) throws DbSvcException;

    /**
     * 根据角色Id列表批量删除
     * @param list
     * @return
     * @throws DbSvcException
     */
    int deleteByRoleIds(List<Long>list) throws DbSvcException;
}
