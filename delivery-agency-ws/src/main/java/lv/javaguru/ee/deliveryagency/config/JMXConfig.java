package lv.javaguru.ee.deliveryagency.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.ConnectorServerFactoryBean;

import javax.management.MalformedObjectNameException;

/**
 * Created by Viktor on 22/09/2014.
 */
@Configuration
@EnableMBeanExport   // enable JMX
public class JMXConfig {

/*
    @Bean
    public ConnectorServerFactoryBean jmxServerConnector() throws MalformedObjectNameException {
        ConnectorServerFactoryBean connector = new ConnectorServerFactoryBean();
        connector.setObjectName("connector:name=rmi");
        connector.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:9999/DELIVERY-AGENCY");
        return connector;
    }
*/

}
