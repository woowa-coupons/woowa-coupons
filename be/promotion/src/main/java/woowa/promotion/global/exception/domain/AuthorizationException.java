package woowa.promotion.global.exception.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import woowa.promotion.global.exception.CustomException;

@Getter
@RequiredArgsConstructor
public enum AuthorizationException implements CustomException {

    EMPTY_TOKEN(HttpStatus.UNAUTHORIZED, "Bearer 토큰 형식에 맞지 않거나 헤더에 토큰이 없습니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    INVALID_ACCESS(HttpStatus.FORBIDDEN, "API 요청 주소가 잘못되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
