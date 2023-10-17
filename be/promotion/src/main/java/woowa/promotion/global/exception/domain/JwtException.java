package woowa.promotion.global.exception.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import woowa.promotion.global.exception.CustomException;

@Getter
@RequiredArgsConstructor
public enum JwtException implements CustomException {

    FAILED_ENCRYPTION(HttpStatus.INTERNAL_SERVER_ERROR, "잘못된 비밀번호입니다."),
    INVALID_FORMAT(HttpStatus.BAD_REQUEST, "Bearer 토큰 형식에 맞지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
    
}
