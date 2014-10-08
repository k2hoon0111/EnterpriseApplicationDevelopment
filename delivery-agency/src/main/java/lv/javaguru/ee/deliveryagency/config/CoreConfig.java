package lv.javaguru.ee.deliveryagency.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Viktor on 07/09/2014.
 */
@Configuration
@ComponentScan(basePackages = {"lv.javaguru.ee.deliveryagency"})
@Import({DataSourceConfig.class, HibernateConfig.class, JPAConfig.class,
        TransactionConfig.class, AppPropertiesConfig.class, JMSConfig.class,
		BackgroundJobConfig.class})
public class CoreConfig {


}
