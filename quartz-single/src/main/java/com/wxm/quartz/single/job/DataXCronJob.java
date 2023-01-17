package com.wxm.quartz.single.job;

import com.wxm.datax.utils.DataXUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/5/5 10:33
 * @since 1.0.0
 */
@Slf4j
@Component
public class DataXCronJob implements Job {
/*    @Autowired
    private DataXPropertiesConfig dataXPropertiesConfig;*/
    @Autowired
    private   DataXUtil dataXUtil;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try{
            SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
            String jobFilePath=context.getJobDetail().getJobDataMap().getString("jobFilePath");
            if(StringUtils.hasText(jobFilePath)){
                dataXUtil.start(jobFilePath,true);
            }
        }catch (Exception e){
            log.error("执行定时任务发生异常:{}",e.getMessage(),e);
            throw new JobExecutionException(e);
        }
    }
}
