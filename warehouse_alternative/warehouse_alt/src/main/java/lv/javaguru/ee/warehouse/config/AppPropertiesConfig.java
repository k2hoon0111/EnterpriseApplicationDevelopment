package lv.javaguru.ee.warehouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Viktor on 07/09/2014.
 */
@Configuration
public class AppPropertiesConfig {

    @Bean
    @Profile("prod")
    public static PropertySourcesPlaceholderConfigurer prodPropertiesPlaceholderConfigurer() {              
        return getPropertiesPlaceholderConfigurer("database-prod.properties");
    }

    @Bean
    @Profile({"test",  "!prod"})
    public static PropertySourcesPlaceholderConfigurer testPropertiesPlaceholderConfigurer() {        
        return getPropertiesPlaceholderConfigurer("database-test.properties");
    }
         
    private static PropertySourcesPlaceholderConfigurer getPropertiesPlaceholderConfigurer(String resourceLocation) {
        PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();       
        p.setLocation(new ClassPathResource(resourceLocation));
        return p;
    }
    
}
