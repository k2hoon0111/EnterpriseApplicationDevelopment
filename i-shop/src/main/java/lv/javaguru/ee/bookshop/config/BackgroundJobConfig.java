package lv.javaguru.ee.bookshop.config;

import lv.javaguru.ee.bookshop.core.jobs.PingHost;
import lv.javaguru.ee.bookshop.core.jobs.ScheduledBackgroundJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

@Configuration
//@EnableAsync
@EnableScheduling
public class BackgroundJobConfig {

    @Autowired
    private ScheduledBackgroundJob scheduledBackgroundJob;

    @Autowired
    private PingHost pingHost;

    // Documentation
    // http://docs.spring.io/spring/docs/current/spring-framework-reference/html/scheduling.html

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.initialize();
        taskScheduler.schedule(scheduledBackgroundJob, new CronTrigger("0/25 * * * * *"));
        taskScheduler.schedule(pingHost, new CronTrigger("0/10 * * * * *"));
        return taskScheduler;
    }

}
