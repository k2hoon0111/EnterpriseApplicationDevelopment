package lv.javaguru.ee.bookshop.integrations.jetty;

import lv.javaguru.ee.bookshop.integrations.PropertiesReader;

/**
 * Created by MumboJumbo on 06/10/14.
 */
public class Main {
    private static EmbeddedJetty jettyServer;

    public static void main(String [] args) throws Exception{
        PropertiesReader propertiesReader = new PropertiesReader();
        int port = Integer.parseInt(propertiesReader.getPort());
        jettyServer = new EmbeddedJetty("/", port);
        jettyServer.start();
    }
}
