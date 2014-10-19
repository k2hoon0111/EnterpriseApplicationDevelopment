package lv.javaguru.ee.bookshop.core.camel;

/**
 * Created by MumboJumbo on 12/10/14.
 */

import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.dataformat.xmljson.XmlJsonDataFormat;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class FromFtpServerRouteBuilder extends SpringRouteBuilder {

    @Override
    public void configure() throws Exception {
        XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
        xmlJsonFormat.setEncoding("UTF-8");
        xmlJsonFormat.setForceTopLevelObject(true);
        xmlJsonFormat.setTrimSpaces(true);
        xmlJsonFormat.setRootName("newRoot");
        xmlJsonFormat.setSkipNamespaces(true);
        xmlJsonFormat.setRemoveNamespacePrefixes(true);
        xmlJsonFormat.setExpandableProperties(Arrays.asList("d", "e"));

        // configure properties component
        PropertiesComponent pc = getContext().getComponent("properties", PropertiesComponent.class);
        pc.setLocation("classpath:ftp.properties");

//        from("{{ftp.server}}")
//                .marshal(xmlJsonFormat)
//                .to("file:/Users/MumboJumbo/camel/?fileName=${file:name.noext}_${date:now:yyyyMMddhhmmssSS}.json")
//                .log("Downloaded file ${file:name} ${file:size} complete.");

        from("direct:start").to("mock:result");

        from("{{ftp.server}}")
                .to("activemq:queue:demo")
                .log("Downloaded file ${file:name} ${file:size} complete.");

    }
}
