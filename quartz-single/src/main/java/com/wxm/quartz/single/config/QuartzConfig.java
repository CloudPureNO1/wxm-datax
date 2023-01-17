package com.wxm.quartz.single.config;


import com.wxm.quartz.single.listener.MyJobListener;
import com.wxm.quartz.single.listener.MySchedulerListener;
import com.wxm.quartz.single.listener.MyTriggerListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.matchers.EverythingMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Properties;

import static org.quartz.impl.matchers.EverythingMatcher.allJobs;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/4/27 13:56
 * @since 1.0.0
 */
@Configuration
public class QuartzConfig implements SchedulerFactoryBeanCustomizer {
    /**
     * 注入 自定义监听，非必须
     */
    @Autowired
    private   MySchedulerListener mySchedulerListener;
    /**
     * 注入 自定义监听，非必须
     */
    @Autowired
    private   MyTriggerListener myTriggerListener;
    /**
     * 注入 自定义监听，非必须
     */
    @Autowired
    private   MyJobListener myJobListener;


    @Autowired
    private MyJobFactory myJobFactory;

    @Bean
    public Properties properties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        // 对quartz.properties文件进行读取
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz-my.properties"));
        // 在quartz.properties中的属性被读取并注入后再初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(myJobFactory);
        schedulerFactoryBean.setQuartzProperties(properties());
        return schedulerFactoryBean;
    }


    /**
     * quartz初始化监听器
     * @return
     */
    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }

    /**
     * 通过SchedulerFactoryBean获取Scheduler的实例
     * @return
     * @throws IOException
     */
    @Bean(name = "scheduler")
    public Scheduler scheduler() throws IOException, SchedulerException {
        //return schedulerFactoryBean().getScheduler();
        /**
         * 非必须
         * 如果添加了自定义的listner,需要注册
         */
        Scheduler scheduler = schedulerFactoryBean().getScheduler();
        //全局添加监听器
        //添加SchedulerListener监听器
        scheduler.getListenerManager().addSchedulerListener(mySchedulerListener);

        // 添加JobListener, 支持带条件匹配监听器
        //scheduler.getListenerManager().addJobListener(myJobListener, KeyMatcher.keyEquals(JobKey.jobKey("myJob", "myGroup")));
        // 添加对所有job感兴趣的JobListener
        scheduler.getListenerManager().addJobListener(myJobListener, allJobs());

        // 添加triggerListener，设置全局监听
        scheduler.getListenerManager().addTriggerListener(myTriggerListener, EverythingMatcher.allTriggers());
        return scheduler;

        /**
         * 这将上面的例子变成这样：
         *
         * scheduler.getListenerManager().addJobListener(myJobListener, jobKeyEquals(jobKey("myJobName", "myJobGroup")));
         * 添加对特定组的所有job感兴趣的JobListener：
         *
         * scheduler.getListenerManager().addJobListener(myJobListener, jobGroupEquals("myJobGroup"));
         * 添加对两个特定组的所有job感兴趣的JobListener：
         *
         * scheduler.getListenerManager().addJobListener(myJobListener, or(jobGroupEquals("myJobGroup"), jobGroupEquals("yourGroup")));
         * 添加对所有job感兴趣的JobListener：
         *
         * scheduler.getListenerManager().addJobListener(myJobListener, allJobs());
         * 注册TriggerListeners的工作原理相同。
         *
         * Quartz的大多数用户并不使用Listeners，但是当应用程序需求创建需要事件通知时不需要Job本身就必须明确地通知应用程序，这些用户就很方便。
         */
    }

    /**
     * 使用阿里的druid作为数据库连接池
     */
    @Override
    public void customize(@NotNull SchedulerFactoryBean schedulerFactoryBean) {
        schedulerFactoryBean.setStartupDelay(2);
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.setOverwriteExistingJobs(true);
    }
}
