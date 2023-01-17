package com.wxm.service.db.quartz.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxm.druid.entity.quartz.DataXDetails;
import com.wxm.druid.mapper.quartz.DataXDetailsMapper;
import com.wxm.service.db.quartz.IDataXDetailsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * datax 定时任务文件信息 服务实现类
 * </p>
 *
 * @author 王森名
 * @since 2022-07-06
 */
@Service
public class DataXDetailsServiceImpl extends ServiceImpl<DataXDetailsMapper, DataXDetails> implements IDataXDetailsService {

}
