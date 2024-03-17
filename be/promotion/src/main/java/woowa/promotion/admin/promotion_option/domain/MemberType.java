package woowa.promotion.admin.promotion_option.domain;

import java.util.Arrays;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.exception.domain.MemberTypeException;

public enum MemberType {

    NEW_MEMBER, OLD_MEMBER;

    public static MemberType from(String type) {
        return Arrays.stream(MemberType.values())
                .filter(couponType -> couponType.name().equalsIgnoreCase(type))
                .findAny()
                .orElseThrow(() -> new ApiException(MemberTypeException.INVALID_MEMBER_TYPE));
    }

}
