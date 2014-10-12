package lv.javaguru.ee.bookshop.core.camel;

/**
 * Created by MumboJumbo on 12/10/14.
 */
import org.apache.camel.main.Main;

/**
 * Main class that can download files from an existing FTP server.
 */
public final class FtpServer {

    private FtpServer() {
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.addRouteBuilder(new FtpServerRouteBuilder());
        main.enableHangupSupport();
        main.run();
    }

}
