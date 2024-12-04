package com.wxm.service.db.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.biz.WxmUserGroup;

import java.util.List;
/**
 * <p>
 * 用户-用户组关联表 服务类
 * </p>
 *
 * @author 王森明
 * @since 2022-06-17
 */
@DS("biz")
public interface IWxmUserGroupService extends IService<WxmUserGroup> {
    /**
     * 根据用户组Id,和用户Id列表，批量删除
     * @param groupId
     * @param list
     * @return
     */
    int deleteBatchByGroupIdAndUserIds(Long groupId,List<Long>list) throws DbSvcException;


    /**
     * 根据用户ID列表批量删除
     * @param list
     * @return
     * @throws DbSvcException
     */
    int deleteBatchByUserList(List<Long> list) throws DbSvcException;

    /**
     * 根据用户组ID列表批量删除
     * @param list
     * @return
     * @throws DbSvcException
     */
    int deleteBatchByGroupIds(List<Long>list)  throws DbSvcException;
}
