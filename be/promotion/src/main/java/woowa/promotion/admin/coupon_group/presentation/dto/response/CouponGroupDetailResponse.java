package woowa.promotion.admin.coupon_group.presentation.dto.response;

import java.time.Instant;
import java.util.List;
import woowa.promotion.admin.coupon.domain.Coupon;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;

public record CouponGroupDetailResponse(

        String title,
        Boolean isRandom,
        Instant startedAt,
        Instant finishedAt,
        List<CouponGroupCouponResponse> coupons
) {
    public static CouponGroupDetailResponse from(CouponGroup couponGroup) {
        return new CouponGroupDetailResponse(
                couponGroup.getTitle(),
                couponGroup.getIsRandom(),
                couponGroup.getStartedAt(),
                couponGroup.getFinishedAt(),
                couponGroup.getCoupons().stream()
                        .map(CouponGroupCouponResponse::from)
                        .toList()
        );
    }

    public record CouponGroupCouponResponse(

            String title,
            String type,
            Integer discount,
            Integer initialQuantity
    ) {
        public static CouponGroupCouponResponse from(Coupon coupon) {
            return new CouponGroupCouponResponse(
                    coupon.getTitle(),
                    coupon.getType().name(),
                    coupon.getDiscount(),
                    coupon.getInitialQuantity()
            );
        }
    }
}
