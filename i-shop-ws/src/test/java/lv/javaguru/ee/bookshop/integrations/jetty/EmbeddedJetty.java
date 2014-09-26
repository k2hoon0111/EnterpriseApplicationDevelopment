package lv.javaguru.ee.bookshop.integrations.jetty;

import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import lv.javaguru.ee.bookshop.config.CoreConfig;
import lv.javaguru.ee.bookshop.config.WebMVCConfig;

import java.lang.management.ManagementFactory;

public class EmbeddedJetty {

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

            final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
            applicationContext.register(CoreConfig.class, WebMVCConfig.class);

            final ServletHolder servletHolder = new ServletHolder(new DispatcherServlet(applicationContext));
            final ServletContextHandler context = new ServletContextHandler();
            context.setContextPath("/");
            context.addServlet(servletHolder, "/*");

            server.setHandler(context);

            getServer().start();
        }
        System.out.println("Started on port: " + httpPort);
    }

    public void stop() throws Exception {
        if (getServer() != null) {
            getServer().stop();
            setServer(null);
        }
    }

    public String getBaseUrl() {
        return "http://localhost:" + httpPort + rootContext;
    }
}