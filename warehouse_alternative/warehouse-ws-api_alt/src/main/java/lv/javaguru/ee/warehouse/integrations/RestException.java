package lv.javaguru.ee.warehouse.integrations;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

/**
 * Created by Viktor on 19/09/2014.
 */
public class RestException extends RuntimeException {

    private Response.StatusType httpStatus;

    public RestException(Throwable e) {
        if(e instanceof ClientErrorException) {
            this.httpStatus = ((ClientErrorException) e).getResponse().getStatusInfo();
        }
    }

    public int getHttpStatus() {
        return httpStatus.getStatusCode();
    }

}
