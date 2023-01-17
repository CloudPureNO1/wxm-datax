package com.wxm.dao.service.db.quartz;


import com.wxm.base.exception.DbSvcException;
import com.wxm.dao.model.ds2.nodb.JobDetailAndTriggerBean;

import java.util.List;
/**
 * <p>
 * qrtz_job_details表 用来存储已配置的Job的详细信息。 服务类
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
public interface IJobDetailsService{
    /**
     * 获取定时任务和触发器的列表
     * @return
     * @throws DbSvcException
     */
    List<JobDetailAndTriggerBean> queryJobDetailAndTriggerList() throws DbSvcException;
}
