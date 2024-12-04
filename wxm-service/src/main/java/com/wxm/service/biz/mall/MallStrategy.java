package com.wxm.service.biz.mall;

import com.wxm.base.exception.BizSvcException;
import com.wxm.base.exception.DbSvcException;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-29 17:57:27
 */
public interface MallStrategy<T,R> {
    R execute(T t) throws BizSvcException, DbSvcException;
}
