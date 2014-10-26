package lv.javaguru.ee.warehouse.config;

import java.util.Arrays;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

/**
 * Created by Viktor on 15/09/2014.
 */
public class SpringWebMvcInitializer extends AbstractDispatcherServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(SpringWebMvcInitializer.class);
    
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext applicationContext =
                new AnnotationConfigWebApplicationContext();
        applicationContext.register(CoreConfig.class);
        return applicationContext;
    }

    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext applicationContext =
                new AnnotationConfigWebApplicationContext();
        applicationContext.register(WebMVCConfig.class);        
        return applicationContext;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/*"};
    }
         
}
