package com.wxm.service.db.biz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.wxm.base.dto.mall.in.Mall11001In;
import com.wxm.base.dto.rbac.in.Rbac11002In;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.biz.MallShop;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wxm.druid.entity.biz.WxmUser;

import java.util.List;

/**
 * <p>
 * 店铺信息表 服务类
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
public interface IMallShopService extends IService<MallShop> {
    List<MallShop> queryAll() throws DbSvcException;
    IPage queryAllPage(int currentPage, int pageSize) throws DbSvcException;
    IPage queryPageByCon(Mall11001In mall11001In) throws DbSvcException;
}
