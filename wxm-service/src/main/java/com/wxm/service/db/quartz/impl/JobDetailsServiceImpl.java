package com.wxm.service.db.quartz.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxm.base.dto.quartz.in.Quartz11002In;
import com.wxm.base.enums.DbSvcEnum;
import com.wxm.base.exception.DbSvcException;

import com.wxm.druid.entity.quartz.JobDetails;
import com.wxm.druid.entity.quartz.Triggers;
import com.wxm.druid.entity.quartz.nodb.JobDetailAndTriggerBean;
import com.wxm.druid.mapper.quartz.JobDetailsMapper;
import com.wxm.druid.mapper.quartz.TriggersMapper;
import com.wxm.service.db.quartz.IJobDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * qrtz_job_details表 用来存储已配置的Job的详细信息。 服务实现类
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@Slf4j
@Service
public class JobDetailsServiceImpl extends ServiceImpl<JobDetailsMapper, JobDetails>  implements IJobDetailsService {
    private final JobDetailsMapper jdmp;
    public JobDetailsServiceImpl(JobDetailsMapper jdmp){
        this.jdmp=jdmp;
    }

    @Override
    public List<JobDetailAndTriggerBean> queryJobDetailAndTriggerList() throws DbSvcException {
        try{
           return jdmp.selectJobDetailAndTriggerList();
        }catch (Exception e){
            log.error("【{}】{}：{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("定时任务列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("定时任务列表"));
        }
    }

    @Override
    public Map<String, String> queryMap(String jobName, String jobGroup) throws DbSvcException {
        try{
            return jdmp.selectMap(jobName,jobGroup);
        }catch (Exception e){
            log.error("【{}】{}：{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("定时任务"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("定时任务"));
        }
    }

}
