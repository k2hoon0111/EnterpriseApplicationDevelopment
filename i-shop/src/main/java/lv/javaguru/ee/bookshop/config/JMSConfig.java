package lv.javaguru.ee.bookshop.config;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* Created by Viktor on 07/10/2014.
*/
@Configuration
public class JMSConfig {

    @Bean
    protected ActiveMQConnectionFactory activemqConnectionFactory() {
        ActiveMQConnectionFactory connFactory = new ActiveMQConnectionFactory();
        connFactory.setBrokerURL("tcp://localhost:61616");
        return connFactory;
    }

}
