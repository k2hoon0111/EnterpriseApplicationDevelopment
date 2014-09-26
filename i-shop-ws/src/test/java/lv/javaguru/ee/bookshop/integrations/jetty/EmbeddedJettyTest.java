package lv.javaguru.ee.bookshop.integrations.jetty;


import lv.javaguru.ee.bookshop.integrations.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * Created by Viktor on 16/09/2014.
 */
public class EmbeddedJettyTest {

    private static EmbeddedJetty jettyServer;


    @BeforeClass
    public static void init() throws Exception {
        jettyServer = new EmbeddedJetty("/", Server.PORT);
        jettyServer.start();
    }

    @AfterClass
    public static void clean() throws Exception {
        jettyServer.stop();
    }

}
