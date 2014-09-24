package lv.javaguru.ee.deliveryagency.integrations;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Viktor on 19/09/2014.
 */
public class PropertiesReader {

    private static final String PROPERTY_FILE = "ws.properties";

    private String baseUrl;


    public PropertiesReader() {
        Properties properties = new Properties();
        try {
            properties.load(PropertiesReader.class.getClassLoader().getResourceAsStream(PROPERTY_FILE));

            baseUrl = properties.getProperty("baseUrl");
        } catch (IOException e){
            System.out.println("Exciption while reading properties from file = " + PROPERTY_FILE);
            e.printStackTrace();
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
