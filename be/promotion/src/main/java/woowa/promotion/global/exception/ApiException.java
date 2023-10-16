package woowa.promotion.global.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{

    private final CustomException customException;

    public ApiException(CustomException customException) {
        this.customException = customException;
    }

    @Override
    public String getMessage() {
        return customException.getMessage();
    }

    public int getStatus() {
        return customException.getHttpStatus().value();
    }

}
