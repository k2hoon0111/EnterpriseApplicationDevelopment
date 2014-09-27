package lv.javaguru.ee.deliveryagency.integrations;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Created by Viktor on 19/09/2014.
 */
public class RestException extends RuntimeException {

    private HttpStatus httpStatus;

    public RestException(Throwable e) {
        if(e instanceof HttpClientErrorException) {
            this.httpStatus = ((HttpClientErrorException) e).getStatusCode();
        }
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
