package woowa.promotion.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ApiException extends RuntimeException {

    private final CustomException customException;

    @Override
    public String getMessage() {
        return customException.getMessage();
    }

    public int getStatus() {
        return customException.getHttpStatus().value();
    }

}
