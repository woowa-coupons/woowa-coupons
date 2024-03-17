package woowa.promotion.global.exception;

import org.springframework.http.HttpStatus;

public interface CustomException {

    HttpStatus getHttpStatus();

    String getMessage();

}
