package lv.javaguru.ee.deliveryagency.integrations.jetty;

import lv.javaguru.ee.deliveryagency.config.WebMVCConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

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

            final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
            applicationContext.register(WebMVCConfig.class);

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