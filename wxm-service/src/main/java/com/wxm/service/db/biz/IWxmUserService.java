package com.wxm.service.db.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.wxm.base.dto.rbac.in.Rbac11002In;
import com.wxm.base.dto.rbac.in.Rbac11003In;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.biz.WxmUser;
import com.wxm.service.db.IDbService;

import java.util.List;
/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/6 10:06
 * @since 1.0.0
 */
@DS("biz")
public interface IWxmUserService extends IDbService, IService<WxmUser> {
   List<WxmUser>  queryAll() throws DbSvcException;
   PageInfo queryAllPage(int currentPage, int pageSize) throws DbSvcException;
   PageInfo queryPageByCon(Rbac11002In rbac11002In) throws DbSvcException;
   PageInfo queryPageByConNonGroup(Rbac11003In rbac11003In) throws DbSvcException;
   int add(WxmUser user) throws DbSvcException;
   int edit(WxmUser user) throws DbSvcException;
   int delete(Long userId) throws DbSvcException;
}
