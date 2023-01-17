package com.wxm.quartz.single.test;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

/**
 * <p>Job状态与并发</p>
 * <p>
 * 关于job的状态数据（即JobDataMap）和并发性，还有一些地方需要注意。在job类上可以加入一些注解，这些注解会影响job的状态和并发性。
 * @DisallowConcurrentExecution：将该注解加到job类上，告诉Quartz不要并发地执行同一个job定义（这里指特定的job类）的多个实例。请注意这里的用词。拿前一小节的例子来说，如果“SalesReportJob”类上有该注解，则同一时刻仅允许执行一个“SalesReportForJoe”实例，但可以并发地执行“SalesReportForMike”类的一个实例。所以该限制是针对JobDetail的，而不是job类的。但是我们认为（在设计Quartz的时候）应该将该注解放在job类上，因为job类的改变经常会导致其行为发生变化。
 * @PersistJobDataAfterExecution：将该注解加在job类上，告诉Quartz在成功执行了job类的execute方法后（没有发生任何异常），更新JobDetail中JobDataMap的数据，使得该job（即JobDetail）在下一次执行的时候，JobDataMap中是更新后的数据，而不是更新前的旧数据。和 @DisallowConcurrentExecution注解一样，尽管注解是加在job类上的，但其限制作用是针对job实例的，而不是job类的。由job类来承载注解，是因为job类的内容经常会影响其行为状态（比如，job类的execute方法需要显式地“理解”其”状态“）。
 * 如果你使用了@PersistJobDataAfterExecution注解，我们强烈建议你同时使用@DisallowConcurrentExecution注解，因为当同一个job（JobDetail）的两个实例被并发执行时，由于竞争，JobDataMap中存储的数据很可能是不确定的
 * </p>
 *
 * @author 王森明
 * @date 2022/4/27 15:38
 * @since 1.0.0
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution

@Slf4j
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("开始 job 测试");

    }
}
