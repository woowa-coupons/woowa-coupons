package woowa.promotion.global.exception.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import woowa.promotion.global.exception.CustomException;

@Getter
@RequiredArgsConstructor
public enum MemberTypeException implements CustomException {
    INVALID_MEMBER_TYPE(HttpStatus.BAD_REQUEST, "잘못된 멤버 타입입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
