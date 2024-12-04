package com.wxm.quartz.job.custom;


import com.wxm.quartz.job.OrdinaryAbstract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/7/11 14:45
 * @since 1.0.0
 */
@Slf4j
@Component
public class TestJob extends OrdinaryAbstract {
    @Value("${quartz.my.ordinary.message}")
    private String quartzTestMsg;
    @Override
    public void execute(String params) {
        log.info("************************************************************************");
        log.info("************************************************************************");
        log.info("********************custom 定时任务实现 开始******************************");
        log.info("************************************************************************");

        log.info("执行结果：{}",this.quartzTestMsg);

        log.info("************************************************************************");
        log.info("************************************************************************");
        log.info("********************custom 定时任务实现 结束******************************");
        log.info("************************************************************************");
        log.info("************************************************************************");
    }
}
