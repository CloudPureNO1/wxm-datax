package com.wxm.quartz.single.test;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/4/27 14:27
 * @since 1.0.0
 */
@Slf4j
public class QuartzTest {
    public static void main(String[] args) throws SchedulerException {

        // application.yml 或者application.properties 只有对应的数据库放在主文件中，才存数据库，否则是不存的，
        // 也可以在有主文件的地方引用quartz的数据源配置 application-quartz.properties  spring.profile.acvice=quartz 也可以存储数据库

            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();
            //dealJob(scheduler);
            // dealJob2(scheduler);
            //dealJob3(scheduler);

            dealJob5(scheduler);
            //scheduler.shutdown();


    }

    /**
     * 在线生成地址：https://cron.qqe2.com/
     *
     *
     * Cron Expressions
     * Cron-Expressions用于配置CronTrigger的实例。Cron Expressions是由七个子表达式组成的字符串，用于描述日程表的各个细节。这些子表达式用空格分隔，并表示：
     *
     * Seconds
     * Minutes
     * Hours
     * Day-of-Month
     * Month
     * Day-of-Week
     * Year (optional field)
     * 一个完整的Cron-Expressions的例子是字符串“0 0 12？* WED“ - 这意味着”每个星期三下午12:00“。
     *
     * 单个子表达式可以包含范围和/或列表。例如，可以用“MON-FRI”，“MON，WED，FRI”或甚至“MON-WED，SAT”代替前一个（例如“WED”）示例中的星期几字段。
     *
     * 通配符（' '字符）可用于说明该字段的“每个”可能的值。因此，前一个例子的“月”字段中的“”字符仅仅是“每个月”。因此，“星期几”字段中的“*”显然意味着“每周的每一天”。
     *
     * 所有字段都有一组可以指定的有效值。这些值应该是相当明显的 - 例如秒和分钟的数字0到59，数小时的值0到23。日期可以是1-31的任何值，但是您需要注意在给定的月份中有多少天！月份可以指定为0到11之间的值，或者使用字符串JAN，FEB，MAR，APR，MAY，JUN，JUL，AUG，SEP，OCT，NOV和DEC。星期几可以指定为1到7（1 =星期日）之间的值，或者使用字符串SUN，MON，TUE，WED，THU，FRI和SAT。
     *
     * '/'字符可用于指定值的增量。例如，如果在“分钟”字段中输入“0/15”，则表示“每隔15分钟，从零开始”。如果您在“分钟”字段中使用“3/20”，则意味着“每隔20分钟，从三分钟开始” - 换句话说，它与“分钟”中的“3,23,43”相同领域。请注意“ / 35”的细微之处并不代表“每35分钟” - 这意味着“每隔35分钟，从零开始” - 或者换句话说，与指定“0,35”相同。
     *
     * '？' 字符是允许的日期和星期几字段。用于指定“无特定值”。当您需要在两个字段中的一个字段中指定某个字符而不是另一个字段时，这很有用。请参阅下面的示例（和CronTrigger JavaDoc）以进行说明。
     *
     * “L”字符允许用于月日和星期几字段。这个角色对于“最后”来说是短暂的，但是在这两个领域的每一个领域都有不同的含义。例如，“月”字段中的“L”表示“月的最后一天” - 1月31日，非闰年2月28日。如果在本周的某一天使用，它只是意味着“7”或“SAT”。但是如果在星期几的领域中再次使用这个值，就意味着“最后一个月的xxx日”，例如“6L”或“FRIL”都意味着“月的最后一个星期五”。您还可以指定从该月最后一天的偏移量，例如“L-3”，这意味着日历月份的第三个到最后一天。当使用'L'选项时，重要的是不要指定列表或值的范围，因为您会得到混乱/意外的结果。
     *
     * “W”用于指定最近给定日期的工作日（星期一至星期五）。例如，如果要将“15W”指定为月日期字段的值，则意思是：“最近的平日到当月15日”。
     *
     * '＃'用于指定本月的“第n个”XXX工作日。例如，“星期几”字段中的“6＃3”或“FRI＃3”的值表示“本月的第三个星期五”。
     *
     * 以下是一些表达式及其含义的更多示例 - 您可以在JavaDoc中找到更多的org.quartz.CronExpression
     * @param scheduler
     * @throws SchedulerException
     */
    private static void dealJob5(Scheduler scheduler) throws SchedulerException {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jsonData","{\"db\":123}");
        JobDetail job= JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1","group1")
                .withDescription("测试job1")
                .setJobData(jobDataMap)
                .build();

        Trigger trigger=TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")
                .withDescription("测试trigger1")
                .startNow()
                // 秒  分  小时 日 月 星期 年
                // 0/5 *   *   *  *  ?
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        scheduler.scheduleJob(job, trigger);
    }
    private static void dealJob3(Scheduler scheduler) throws SchedulerException {
        JobDetail job= JobBuilder.newJob(TestJob.class)
                .withIdentity("job4","group4")
                .withDescription("测试job4")
                .build();

        Trigger trigger=TriggerBuilder.newTrigger()
                .withIdentity("trigger4","group4")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
                .build();
        scheduler.scheduleJob(job, trigger);
    }

    private static void dealJob2(Scheduler scheduler) throws SchedulerException {
        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(TestJob.class)
                .withIdentity("job2", "group2")
                .build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = newTrigger()
                .withIdentity("trigger2", "group2")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);
    }
    private static void dealJob(Scheduler scheduler) throws SchedulerException {
        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);
    }


}
