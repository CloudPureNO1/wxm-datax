package com.wxm.service.biz.mall;

import com.wxm.base.dto.mall.in.Mall14001In;
import com.wxm.base.dto.mall.out.Mall14001Out;
import com.wxm.base.exception.BizSvcException;
import com.wxm.base.exception.DbSvcException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-06-18 15:17:31
 */
@Component
public class Mall14001Strategy implements MallStrategy<Mall14001In, Mall14001Out> {



    @Override
    public Mall14001Out execute(Mall14001In mall14001In) throws BizSvcException, DbSvcException {
        return null;
    }
}
