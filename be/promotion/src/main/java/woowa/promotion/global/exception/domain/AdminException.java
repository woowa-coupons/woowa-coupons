package woowa.promotion.global.exception.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import woowa.promotion.global.exception.CustomException;

@Getter
@RequiredArgsConstructor
public enum AdminException implements CustomException {

    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "중복된 이메일입니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
