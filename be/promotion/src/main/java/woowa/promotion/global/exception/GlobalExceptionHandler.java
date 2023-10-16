package woowa.promotion.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import woowa.promotion.global.exception.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> apiExceptionHandler(ApiException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(ErrorResponse.fail(ex.getStatus(), ex.getMessage()));
    }
    
}
