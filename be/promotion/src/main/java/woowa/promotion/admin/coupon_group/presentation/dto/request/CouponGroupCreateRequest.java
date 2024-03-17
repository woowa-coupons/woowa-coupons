package woowa.promotion.admin.coupon_group.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import woowa.promotion.admin.coupon.domain.Coupon;
import woowa.promotion.admin.coupon.domain.CouponType;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.admin.coupon_group.domain.Type;

public record CouponGroupCreateRequest(

        String title,
        Instant startedAt,
        Instant finishedAt,
        String type,
        Boolean isRandom,

        @JsonProperty("coupons")
        List<CouponDto> couponDtos
) {
    public CouponGroup toCouponGroup(String adminNickname) {
        return CouponGroup.builder()
                .title(title)
                .startedAt(startedAt)
                .finishedAt(finishedAt)
                .adminNickname(adminNickname)
                .type(Type.from(type))
                .isRandom(isRandom)
                .build();
    }

    public List<Coupon> toCoupons(CouponGroup couponGroup) {
        return couponDtos.stream()
                .map(couponDto -> toCoupon(couponDto, couponGroup))
                .collect(Collectors.toList());
    }

    private Coupon toCoupon(CouponDto couponDto, CouponGroup couponGroup) {
        return Coupon.builder()
                .title(couponDto.title())
                .discount(couponDto.discount())
                .type(CouponType.from(couponDto.type()))
                .initialQuantity(couponDto.initialQuantity())
                .couponGroup(couponGroup)
                .build();
    }

    public record CouponDto(

            String title,
            String type,
            int discount,
            int initialQuantity
    ) {
    }

}
