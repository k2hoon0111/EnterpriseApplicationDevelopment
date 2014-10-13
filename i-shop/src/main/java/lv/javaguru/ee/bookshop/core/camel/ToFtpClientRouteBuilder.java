package lv.javaguru.ee.bookshop.core.camel;

/**
 * Created by MumboJumbo on 12/10/14.
 */

import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ToFtpClientRouteBuilder extends SpringRouteBuilder {

    @Override
    public void configure() throws Exception {
        // configure properties component
        PropertiesComponent pc = getContext().getComponent("properties", PropertiesComponent.class);
        pc.setLocation("classpath:ftp.properties");

        from("file:target/upload?moveFailed=../error")
                .log("Uploading file ${file:name}")
                .to("{{ftp.client}}")
                .log("Uploaded file ${file:name} complete.");
    }
}
