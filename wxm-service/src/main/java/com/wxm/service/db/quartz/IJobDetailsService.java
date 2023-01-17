package com.wxm.service.db.quartz;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.wxm.base.dto.quartz.in.Quartz11002In;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.quartz.JobDetails;
import com.wxm.druid.entity.quartz.Triggers;
import com.wxm.druid.entity.quartz.nodb.JobDetailAndTriggerBean;
import com.wxm.service.db.IDbService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * qrtz_job_details表 用来存储已配置的Job的详细信息。 服务类
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
public interface IJobDetailsService  extends IDbService, IService<JobDetails> {
    /**
     * 获取定时任务和触发器的列表
     * @return
     * @throws DbSvcException
     */
    List<JobDetailAndTriggerBean> queryJobDetailAndTriggerList() throws DbSvcException;

    Map<String,String> queryMap(String jobName, String jobGroup) throws DbSvcException;
}
