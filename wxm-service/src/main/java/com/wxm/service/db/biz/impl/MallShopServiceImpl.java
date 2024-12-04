package com.wxm.service.db.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxm.base.dto.mall.in.Mall11001In;
import com.wxm.base.enums.DbSvcEnum;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.biz.MallShop;
import com.wxm.druid.mapper.biz.MallShopMapper;
import com.wxm.service.db.biz.IMallShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 店铺信息表 服务实现类
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@Slf4j
@Service
public class MallShopServiceImpl extends ServiceImpl<MallShopMapper, MallShop> implements IMallShopService {

    @Override
    public List<MallShop> queryAll() throws DbSvcException {
        try{
            return this.list();
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("店铺列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("店铺列表"),e.getMessage());
        }
    }

    @Override
    public IPage queryAllPage(int currentPage, int pageSize) throws DbSvcException {
        try{
            IPage<MallShop> iPage = new Page<>(currentPage, pageSize);


            QueryWrapper<MallShop> query = new QueryWrapper<>();

            return  baseMapper.selectPage(iPage,query);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("店铺列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("店铺列表"),e.getMessage());
        }
    }

    @Override
    public IPage queryPageByCon(Mall11001In mall11001In) throws DbSvcException {
        try{
            IPage<MallShop> iPage = new Page<>(mall11001In.getCurrentPage(), mall11001In.getPageSize());


            QueryWrapper<MallShop> query = new QueryWrapper<>();
            if (StringUtils.hasText(mall11001In.getShopId())) {
                query.lambda().eq(MallShop::getShopId,mall11001In.getShopId());
            }
            if (StringUtils.hasText(mall11001In.getShopName())) {
                query.lambda().eq(MallShop::getShopName,mall11001In.getShopName());
            }
            if (StringUtils.hasText(mall11001In.getLocation())) {
                query.lambda().eq(MallShop::getLocation,mall11001In.getLocation());
            }
            if (StringUtils.hasText(mall11001In.getUserId())) {
                query.lambda().eq(MallShop::getUserId,mall11001In.getUserId());
            }

            return  baseMapper.selectPage(iPage,query);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("店铺列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("店铺列表"),e.getMessage());
        }
    }
}
