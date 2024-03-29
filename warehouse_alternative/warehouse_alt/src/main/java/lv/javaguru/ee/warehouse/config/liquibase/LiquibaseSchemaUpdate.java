package lv.javaguru.ee.warehouse.config.liquibase;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class LiquibaseSchemaUpdate {

	private static final Logger log = LoggerFactory.getLogger(LiquibaseSchemaUpdate.class);
	
	@Autowired
	private DatabaseMigrationService databaseMigrationService;
	
	@Value("${liquibase.update:true}")
	private boolean enabled;

	@Value("${liquibase.context:main}")
	private String context;

	
	public void execute(DataSource dataSource) {
		if (enabled) {
			updateSchema(dataSource);
		}
	}

	private void updateSchema(DataSource dataSource) {
		StopWatch watch = watchTime();

                log.info("Liquibase updating shema...");
                
		databaseMigrationService.update(dataSource, context);

		log.info("Database populated in {} ms", elapsedTimeInMillis(watch));
	}

	private StopWatch watchTime() {
		StopWatch watch = new StopWatch();
		watch.start();
		return watch;
	}

	private long elapsedTimeInMillis(StopWatch watch) {
		watch.stop();
		return watch.getTotalTimeMillis();
	}
	
}
