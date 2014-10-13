package lv.javaguru.ee.warehouse.integrations.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Viktor on 15/09/2014.
 */
@ControllerAdvice
public class AppRestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(AppRestExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
        Exception ire = (Exception) e;

        log.error("Exception in rest service invocation!", e);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        ErrorBean errorBean = new ErrorBean(HttpStatus.UNPROCESSABLE_ENTITY.value(), ire.getMessage());
        return handleExceptionInternal(e, errorBean, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    private class ErrorBean {
        private String message;
        private Integer code;
        
        public ErrorBean(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }
    }
    
}
