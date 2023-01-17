package com.wxm.dao.service.db.quartz.impl;

import com.wxm.base.enums.DbSvcEnum;
import com.wxm.base.exception.DbSvcException;
import com.wxm.dao.mapper.ds2.JobDetailsMapper;
import com.wxm.dao.model.ds2.nodb.JobDetailAndTriggerBean;
import com.wxm.dao.service.db.quartz.IJobDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
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
public class JobDetailsServiceImpl implements IJobDetailsService {
    private final JobDetailsMapper jdmp;
    public JobDetailsServiceImpl(JobDetailsMapper jdmp){
        this.jdmp=jdmp;
    }

    @Override
    public List<JobDetailAndTriggerBean> queryJobDetailAndTriggerList() throws DbSvcException {
        try{
           return jdmp.selectJobDetailAndTriggerList();
        }catch (Exception e){
            log.error("【{}】{}", DbSvcEnum.DB_SVC_1004.toString(),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1004.toString());
        }
    }
}
