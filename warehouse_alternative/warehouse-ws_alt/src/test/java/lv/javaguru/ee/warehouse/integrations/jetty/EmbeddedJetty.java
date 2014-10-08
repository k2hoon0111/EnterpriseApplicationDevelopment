package lv.javaguru.ee.warehouse.integrations.jetty;

import static java.lang.String.format;
import static java.lang.Thread.sleep;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import lv.javaguru.ee.warehouse.config.CoreConfig;
import lv.javaguru.ee.warehouse.config.WebMVCConfig;

import java.lang.management.ManagementFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.AbstractEnvironment;

public class EmbeddedJetty {

    private static final Logger LOG = LoggerFactory.getLogger(EmbeddedJetty.class);
    
    private final String rootContext;
    private final int httpPort;
    private Server server;

    public EmbeddedJetty(String rootContext, int httpPort) {
        this.rootContext = rootContext;
        this.httpPort = httpPort;
    }

    protected void setServer(Server server) {
        this.server = server;
    }

    protected Server getServer() {
        return this.server;
    }

    public void start() throws Exception {
        if (getServer() == null) {
            setServer(new Server(httpPort));

            // Setup JMX
            MBeanContainer mbContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
            server.addEventListener(mbContainer);
            server.addBean(mbContainer);

            System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "test");
            final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
            applicationContext.register(CoreConfig.class, WebMVCConfig.class);
                        
            final ServletHolder servletHolder = new ServletHolder(new DispatcherServlet(applicationContext));
            final ServletContextHandler context = new ServletContextHandler();
            context.setContextPath("/");
            context.addServlet(servletHolder, "/*");

            server.setHandler(context);

            getServer().start();
        }
        
        LOG.info("Started on port: {}", httpPort);        
    }

    public void stop() throws Exception {
        if (getServer() != null) {
            getServer().stop();
            setServer(null);
            sleep(3000);
        }
    }

    public String getBaseUrl() {
        return format("http://localhost:%s%s",httpPort, rootContext);
    }
}