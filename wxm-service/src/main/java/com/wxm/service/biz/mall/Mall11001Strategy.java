package com.wxm.service.biz.mall;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wxm.base.dto.mall.in.Mall11001In;
import com.wxm.base.dto.mall.out.Mall11001Out;
import com.wxm.base.dto.mall.out.item.Item11001Out;
import com.wxm.base.exception.BizSvcException;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.biz.MallShop;
import com.wxm.service.biz.TemplateWithParams2;
import com.wxm.service.db.biz.IMallShopService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-06-18 15:17:31
 */
@Component
public class Mall11001Strategy implements MallStrategy<Mall11001In, Mall11001Out>{
    private final IMallShopService mallShopService;

    Mall11001Strategy(IMallShopService mallShopService) {
        this.mallShopService = mallShopService;
    }


    @Override
    public Mall11001Out execute(Mall11001In mall11001In) throws BizSvcException, DbSvcException{
        Service11001 service11001=new Service11001();
        return service11001.service(mall11001In,MallShop.class, Item11001Out.class, "店铺列表");
    }

    class Service11001 extends TemplateWithParams2<Mall11001Out,Mall11001In, Item11001Out> {
        @Override
        public IPage<?> queryPage(Mall11001In mall11001In) throws DbSvcException {
            return mallShopService.queryPageByCon(mall11001In);
        }

        @Override
        public Mall11001Out initOut(List<Item11001Out> list, long total) {
            return new Mall11001Out(list,total);
        }
    }
}
