package woowa.promotion.global.exception.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import woowa.promotion.global.exception.CustomException;

@Getter
@RequiredArgsConstructor
public enum BadRequestException implements CustomException {

    COUPON_GROUP_TYPE_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 쿠폰 그룹 타입 입니다. EVERYDAY 혹은 PERIOD로 요청해주세요.");

    private final HttpStatus httpStatus;
    private final String message;

}
