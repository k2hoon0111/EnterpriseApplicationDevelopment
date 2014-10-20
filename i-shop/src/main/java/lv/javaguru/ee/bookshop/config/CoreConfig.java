package lv.javaguru.ee.bookshop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Viktor on 07/09/2014.
 */
@Configuration
@ComponentScan(basePackages = {"lv.javaguru.ee.bookshop"})
@Import({DataSourceConfig.class,
    HibernateConfig.class,
    JPAConfig.class,
    TransactionConfig.class,
    AppPropertiesConfig.class,
    CamelConfig.class,
    JMSConfig.class,
    BackgroundJobConfig.class,
    MongoConfig.class})
public class CoreConfig {

}
