package lv.javaguru.ee.warehouse.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

/**
 * Created by Viktor on 07/09/2014.
 */
@Configuration
@ComponentScan(basePackages = {"lv.javaguru.ee.warehouse"})
@Import({AppPropertiesConfig.class, DataSourceConfig.class, 
    HibernateConfig.class, TransactionConfig.class, })
public class CoreConfig {


    public static void main(String[] args) {
        
        //System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "prod");
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "prod");
        
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(CoreConfig.class);

    }
    
}
