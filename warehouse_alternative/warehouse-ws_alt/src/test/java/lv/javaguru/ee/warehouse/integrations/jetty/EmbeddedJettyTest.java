package lv.javaguru.ee.warehouse.integrations.jetty;


import lv.javaguru.ee.warehouse.integrations.PropertiesReader;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * Created by Viktor on 16/09/2014.
 */
public class EmbeddedJettyTest {

    private static EmbeddedJetty jettyServer;

    @BeforeClass
    public static void init() throws Exception {
        PropertiesReader propertiesReader = new PropertiesReader();
        int port = Integer.parseInt(propertiesReader.getPort());
        jettyServer = new EmbeddedJetty("/", port);
        jettyServer.start();
    }
   
    @AfterClass
    public static void clean() throws Exception {
        jettyServer.stop();
    }

}
