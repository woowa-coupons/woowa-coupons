package woowa.promotion.admin.coupon_group.domain;

import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.exception.domain.BadRequestException;

import java.util.Arrays;

public enum Type {

    EVERYDAY, PERIOD;

    public static Type from(String requestType) {
        return Arrays.stream(Type.values())
                .filter(type -> type.name().equalsIgnoreCase(requestType))
                .findFirst()
                .orElseThrow(() -> new ApiException(BadRequestException.COUPON_GROUP_TYPE_NOT_FOUND));
    }

}
