package woowa.promotion.global.exception.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import woowa.promotion.global.exception.CustomException;

@Getter
@RequiredArgsConstructor
public enum CouponException implements CustomException {

    INVALID_COUPON_TYPE(HttpStatus.BAD_REQUEST, "잘못된 쿠폰 타입입니다."),
    ALREADY_ISSUED(HttpStatus.BAD_REQUEST, "이미 발급된 쿠폰입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "쿠폰을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
