package lv.javaguru.ee.deliveryagency.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import lv.javaguru.ee.deliveryagency.core.jobs.ScheduledBackgroundJob;

@Configuration
//@EnableAsync
//@EnableScheduling
public class BackgroundJobConfig {

	@Autowired
	private ScheduledBackgroundJob scheduledBackgroundJob;
	
	// Documentation
	// http://docs.spring.io/spring/docs/current/spring-framework-reference/html/scheduling.html
	
	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(10);
		taskScheduler.initialize();
		taskScheduler.schedule(scheduledBackgroundJob, new CronTrigger("0/1 * * * * *"));				
		return taskScheduler;				
	}
	
}
