package woowa.promotion.admin.coupon_group.presentation.dto.response;

import java.util.List;

public record CouponGroupSimpleResponse(

        List<CouponGroupSimpleDto> couponGroups,
        Boolean hasNext
) {

    public record CouponGroupSimpleDto(

            Long id,
            String title
    ) {
    }
}
