package com.wxm.service.db.biz.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxm.druid.entity.biz.WxmOperateLog;
import com.wxm.druid.mapper.biz.WxmOperateLogMapper;
import com.wxm.druid.mapper.biz.WxmUserMapper;
import com.wxm.service.db.biz.IWxmOperateLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author 王森明
 * @since 2024-06-13
 */
@Service
public class WxmOperateLogServiceImpl extends ServiceImpl<WxmOperateLogMapper, WxmOperateLog> implements IWxmOperateLogService {

}
