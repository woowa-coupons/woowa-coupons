package woowa.promotion.global.exception.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import woowa.promotion.global.exception.CustomException;

@Getter
@RequiredArgsConstructor
public enum MemberException implements CustomException {

    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    INVALID_EMAIL(HttpStatus.UNAUTHORIZED, "존재하지 않는 이메일입니다. "),
    DUPLICATED_EMAIL(HttpStatus.UNAUTHORIZED, "중복된 이메일입니다.");
    
    private final HttpStatus httpStatus;
    private final String message;

}
