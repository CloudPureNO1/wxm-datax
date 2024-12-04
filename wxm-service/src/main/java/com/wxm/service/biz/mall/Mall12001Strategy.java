package com.wxm.service.biz.mall;

import com.wxm.base.dto.mall.in.Mall12001In;
import com.wxm.base.dto.mall.out.Mall12001Out;
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
public class Mall12001Strategy implements MallStrategy<Mall12001In, Mall12001Out> {
    @Override
    public Mall12001Out execute(Mall12001In mall12001In) throws BizSvcException, DbSvcException {
        return null;
    }
}
