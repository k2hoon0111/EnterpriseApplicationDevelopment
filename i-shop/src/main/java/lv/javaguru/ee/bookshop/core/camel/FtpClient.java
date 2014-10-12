package lv.javaguru.ee.bookshop.core.camel;

/**
 * Created by MumboJumbo on 12/10/14.
 */
import org.apache.camel.main.Main;

/**
 * Main class that can upload files to an existing FTP server.
 */
public final class FtpClient {

    private FtpClient() {
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.addRouteBuilder(new FtpClientRouteBuilder());
        main.enableHangupSupport();
        main.run();
    }

}
