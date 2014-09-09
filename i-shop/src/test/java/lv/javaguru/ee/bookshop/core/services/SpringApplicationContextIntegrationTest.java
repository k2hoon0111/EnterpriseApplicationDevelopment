package lv.javaguru.ee.bookshop.core.services;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Viktor on 27/07/2014.
 */
public class SpringApplicationContextIntegrationTest {

    @Test
    public void contextShouldStartCorrectly() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"integrationTestApplicationContext.xml"});
        assertNotNull(context);
    }

}
