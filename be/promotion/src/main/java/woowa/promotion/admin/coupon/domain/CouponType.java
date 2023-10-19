package woowa.promotion.admin.coupon.domain;

import java.util.Arrays;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.exception.domain.CouponException;

public enum CouponType {

    FIXED, RATE;

    public static CouponType from(String type) {
        return Arrays.stream(CouponType.values())
                .filter(couponType -> couponType.name().equalsIgnoreCase(type))
                .findAny()
                .orElseThrow(() -> new ApiException(CouponException.INVALID_COUPON_TYPE));
    }
}
