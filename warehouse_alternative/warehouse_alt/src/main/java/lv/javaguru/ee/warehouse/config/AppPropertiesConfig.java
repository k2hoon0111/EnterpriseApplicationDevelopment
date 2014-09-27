package lv.javaguru.ee.warehouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created by Viktor on 07/09/2014.
 */
@Configuration
public class AppPropertiesConfig {

    @Bean
    @Profile("prod")
    public static PropertySourcesPlaceholderConfigurer prodPropertiesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
        Resource[] resourceLocations = new Resource[] {
                new ClassPathResource("database.properties")
        };
        p.setLocations(resourceLocations);
        return p;
    }

    @Bean
    @Profile("test")
    public static PropertySourcesPlaceholderConfigurer testPropertiesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
        Resource[] resourceLocations = new Resource[] {
                new ClassPathResource("database-test.properties")
        };
        p.setLocations(resourceLocations);
        return p;
    }

}
