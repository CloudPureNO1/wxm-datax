package com.wxm.service.db.master;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxm.base.exception.BizSvcException;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.master.WxmRoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色-资源关联表 服务类
 * </p>
 *
 * @author 王森明
 * @since 2022-06-17
 */
public interface IWxmRoleResourceService extends IService<WxmRoleResource> {
    /**
     * 根据角色Id列表批量删除
     * @param list
     * @return
     * @throws DbSvcException
     */
    int deleteByRoleIds(List<Long> list) throws DbSvcException;
}
