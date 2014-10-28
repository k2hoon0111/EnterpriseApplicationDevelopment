package lv.javaguru.ee.warehouse.core.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */


@Component
public class ScheduledBackgroundJob {
    
    private static final Logger log = LoggerFactory.getLogger(ScheduledBackgroundJob.class);
    
    @Async
    @Scheduled(cron = "0/5 * * * * ?")
    public void doBackgroundJob() {
        
        log.info("Background job running...");
        
    }
    
}
