package com.wxm.core.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wxm.druid.entity.biz.WxmApi;
import com.wxm.druid.entity.biz.WxmApiLog;
import com.wxm.druid.entity.biz.WxmOperateLog;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-06-17 16:28:28
 */
@DS("biz")
public interface ApiLogService {
    /**
     * 保存日志
     * @param apiLog
     */
    void saveLog(WxmApiLog apiLog);

    /**
     * 更新日志
     * @param apiLog
     */
    void upLog(WxmApiLog apiLog);

    void setOperatorMsg(WxmApiLog apiLog);
}
