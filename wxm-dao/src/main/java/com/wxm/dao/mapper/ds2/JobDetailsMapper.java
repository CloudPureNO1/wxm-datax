package com.wxm.dao.mapper.ds2;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.dao.model.ds2.JobDetails;
import com.wxm.dao.model.ds2.nodb.JobDetailAndTriggerBean;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * <p>
 * qrtz_job_details表 用来存储已配置的Job的详细信息。 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@Repository  // 没有此注解，自动注入的时候会爆红色下划线，  或者使用@Resource 自动注入
public interface JobDetailsMapper extends BaseMapper<JobDetails> {
    /**
     * 查询定时任务和触发器基本信息
     * @return
     */
     List<JobDetailAndTriggerBean> selectJobDetailAndTriggerList();

     Long getCount();
}
