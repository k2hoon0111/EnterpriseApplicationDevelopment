package lv.javaguru.ee.warehouse.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 *
 * @author dell
 */
@Configuration
@EnableAsync()
@EnableScheduling
public class BackgroundJobConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskScheduler());        
    }
       
    @Bean
    public Executor taskScheduler() {
        return Executors.newScheduledThreadPool(5);
    }
    
}
