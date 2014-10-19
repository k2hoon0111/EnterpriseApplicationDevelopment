package lv.javaguru.ee.bookshop.config;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* Created by Viktor on 07/10/2014.
*/
@Configuration
public class JMSConfig {

    @Bean
    protected ActiveMQConnectionFactory activemqConnectionFactory(ApplicationContext applicationContext) {
        ActiveMQConnectionFactory connFactory = new ActiveMQConnectionFactory();
//        connFactory.setBrokerURL("tcp://localhost:61616?wireFormat.maxInactivityDurationInitalDelay=30000");
        connFactory.setBrokerURL("vm://myBroker");
//        <transportConnector name="vm" uri="vm://myBroker"/>
//        camelContext.addComponent("activemq", activeMQComponent("vm://localhost?broker.persistent=false"));
        return connFactory;
    }

}
