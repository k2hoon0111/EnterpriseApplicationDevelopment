package lv.javaguru.ee.bookshop.core.camel;

/**
 * Created by MumboJumbo on 12/10/14.
 */

import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.dataformat.xmljson.XmlJsonDataFormat;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Server route
 */
@Component
public class FtpServerRouteBuilder extends SpringRouteBuilder {

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

        // lets shutdown faster in case of in-flight messages stack up
        getContext().getShutdownStrategy().setTimeout(10);

        from("{{ftp.server}}")
//                .split().tokenizeXML("category")
                .marshal(xmlJsonFormat)
                .to("file:/Users/MumboJumbo/camel/")
                .log("Downloaded file ${file:name} ${file:size} complete.");
//        split().tokenizeXML("row").streaming()
//        split().tokenizeXML("categoryDTO").streaming()
//        .convertBodyTo(CategoryDTO.class)
//        from("{{ftp.server}}")
//                .to("file:/Users/MumboJumbo/camel/")
//                .split().tokenizeXML("row").streaming().marshal(xmlJsonFormat).
//                to("mock:json");
//        .log("Downloaded file ${file:name} ${file:size} complete.");
// use system out so it stand out
    }
}
