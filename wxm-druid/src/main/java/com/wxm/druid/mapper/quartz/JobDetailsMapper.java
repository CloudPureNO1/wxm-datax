package com.wxm.druid.mapper.quartz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.quartz.JobDetails;
import com.wxm.druid.entity.quartz.nodb.JobDetailAndTriggerBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * qrtz_job_details表 用来存储已配置的Job的详细信息。 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@Mapper
@Repository
@DS("quartz")
public interface JobDetailsMapper extends BaseMapper<JobDetails> {
    /**
     * 查询定时任务和触发器基本信息
     * @return
     */
     List<JobDetailAndTriggerBean> selectJobDetailAndTriggerList();

     Long getCount();

     Map<String,String> selectMap(@Param("jobName")String jobName, @Param("jobGroup")String jobGroup);
}
