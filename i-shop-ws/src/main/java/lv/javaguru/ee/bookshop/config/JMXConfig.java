package lv.javaguru.ee.bookshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;

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
