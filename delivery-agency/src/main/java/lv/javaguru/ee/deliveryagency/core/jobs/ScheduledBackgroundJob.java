package lv.javaguru.ee.deliveryagency.core.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ScheduledBackgroundJob implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(ScheduledBackgroundJob.class);
	
	@Override
	public void run() {
		log.info("ScheduledBackgroundTask started!");
	}
	
}
