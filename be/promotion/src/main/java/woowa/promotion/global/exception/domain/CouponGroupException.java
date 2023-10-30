package woowa.promotion.global.exception.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import woowa.promotion.global.exception.CustomException;

@Getter
@RequiredArgsConstructor
public enum CouponGroupException implements CustomException {

    NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 쿠폰 그룹 입니다."),
    EXPIRED_COUPON_GROUP(HttpStatus.BAD_REQUEST, "발급 가능 기간이 지난 쿠폰 그룹 입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
