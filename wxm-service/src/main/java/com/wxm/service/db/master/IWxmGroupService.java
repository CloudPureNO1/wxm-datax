package com.wxm.service.db.master;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.wxm.base.dto.rbac.in.Rbac21002In;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.master.WxmGroup;
import com.wxm.service.db.IDbService;

import java.util.List;
/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/6 10:40
 * @since 1.0.0
 */
public interface IWxmGroupService extends IDbService , IService<WxmGroup> {
    List<WxmGroup>queryAll() throws DbSvcException;
    PageInfo queryAllPage(int currentPage, int pageSize) throws DbSvcException;
    PageInfo queryPageByCon(Rbac21002In rbac21002In) throws DbSvcException;
    int add(WxmGroup group) throws DbSvcException;
    int edit(WxmGroup group) throws DbSvcException;
    int delete(Long groupId) throws DbSvcException;
}
