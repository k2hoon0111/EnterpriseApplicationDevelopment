package lv.javaguru.ee.bookshop.config;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
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
    public SpringCamelContext getCamelContext(ApplicationContext applicationContext) throws Exception {
        CamelContextFactoryBean camelContextFactory = new CamelContextFactoryBean();
        camelContextFactory.setId("camelContext");
        camelContextFactory.setApplicationContext(applicationContext);
//        camelContextFactory.setAutoStartup("autoStartup");
        return camelContextFactory.getContext(true);
    }

    @Bean
    protected ActiveMQConnectionFactory activemqConnectionFactory() {
        ActiveMQConnectionFactory connFactory = new ActiveMQConnectionFactory();
        connFactory.setBrokerURL("tcp://localhost:61616");
        return connFactory;
    }

}
