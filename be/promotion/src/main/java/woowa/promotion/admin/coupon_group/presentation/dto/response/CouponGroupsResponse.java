package woowa.promotion.admin.coupon_group.presentation.dto.response;

import woowa.promotion.admin.coupon.domain.Coupon;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;

import java.util.List;
import java.util.Set;

public record CouponGroupsResponse(

        Long id,
        String title,
        Boolean isRandom,
        List<String> couponTitles,
        Integer totalRemainQuantity,
        Integer totalInitialQuantity,
        String createdBy
) {

    public static CouponGroupsResponse of(CouponGroup couponGroup) {
        Set<Coupon> coupons = couponGroup.getCoupons();
        return new CouponGroupsResponse(
                couponGroup.getId(),
                couponGroup.getTitle(),
                couponGroup.getIsRandom(),
                coupons.stream()
                        .map(Coupon::getTitle)
                        .toList(),
                coupons.stream()
                        .mapToInt(Coupon::getRemainQuantity)
                        .sum(),
                coupons.stream()
                        .mapToInt(Coupon::getInitialQuantity)
                        .sum(),
                couponGroup.getAdminNickname()
        );
    }
}
