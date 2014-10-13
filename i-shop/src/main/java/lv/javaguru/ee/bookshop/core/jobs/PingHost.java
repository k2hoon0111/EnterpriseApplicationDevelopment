package lv.javaguru.ee.bookshop.core.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * Created by MumboJumbo on 14/10/14.
 */
@Component
public class PingHost implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(PingHost.class);

    @Override
    public void run() {
        try {
            String host = "nikolay.lv";
            InetAddress address = InetAddress.getByName(host);

            boolean reachable = address.isReachable(10000);
            if (reachable) {
                log.info("Host: " + host + " is reachable");
            } else {
                log.info("Host: " + host + " is unreachable");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
