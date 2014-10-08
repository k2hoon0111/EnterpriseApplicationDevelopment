package lv.javaguru.ee.deliveryagency.config;

import org.apache.camel.spring.CamelContextFactoryBean;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Viktor on 07/10/2014.
 */
@Configuration
public class JMSConfig {

    @Bean
    public SpringCamelContext getCamelContext(ApplicationContext applicationContext) {
        CamelContextFactoryBean camelContextFactory = new CamelContextFactoryBean();
        camelContextFactory.setId("camelContext");
        camelContextFactory.setApplicationContext(applicationContext);
        return camelContextFactory.getContext(true);
    }

}
