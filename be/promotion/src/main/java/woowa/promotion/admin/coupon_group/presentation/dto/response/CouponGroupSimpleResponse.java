package woowa.promotion.admin.coupon_group.presentation.dto.response;

import java.util.List;

public record CouponGroupSimpleResponse(

        List<CouponGroupSliceDto> couponGroups,
        Boolean hasNext
) {

    public record CouponGroupSliceDto(

            Long id,
            String title
    ) {
    }
}
