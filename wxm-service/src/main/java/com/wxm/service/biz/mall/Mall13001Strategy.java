package com.wxm.service.biz.mall;

import com.wxm.base.dto.mall.in.Mall13001In;
import com.wxm.base.dto.mall.out.Mall13001Out;
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
public class Mall13001Strategy implements MallStrategy<Mall13001In, Mall13001Out> {

    @Override
    public Mall13001Out execute(Mall13001In mall13001In) throws BizSvcException, DbSvcException {
        return null;
    }
}
