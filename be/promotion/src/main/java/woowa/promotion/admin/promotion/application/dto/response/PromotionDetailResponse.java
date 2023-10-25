package woowa.promotion.admin.promotion.application.dto.response;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.admin.promotion.domain.Promotion;
import woowa.promotion.admin.promotion_option.domain.PromotionOption;

public record PromotionDetailResponse(

        String title,
        String content,
        String bannerUrl,
        Instant startedAt,
        Instant finishedAt,
        Boolean isDisplay,
        String progressStatus,
        String promotionPageUrl,
        List<PromotionOptionResponse> promotionOptions
) {

    public static PromotionDetailResponse of(Promotion promotion,
                                             List<PromotionOption> promotionOption,
                                             List<CouponGroup> couponGroups) {
        return new PromotionDetailResponse(
                promotion.getTitle(),
                promotion.getContent(),
                promotion.getBannerUrl(),
                promotion.getStartedAt(),
                promotion.getFinishedAt(),
                promotion.getIsDisplay(),
                promotion.getProgressStatus().name(),
                promotion.getPromotionPageUrl(),
                makePromotionOptionResponse(promotionOption, couponGroups)
        );
    }

    private static List<PromotionOptionResponse> makePromotionOptionResponse(List<PromotionOption> promotionOptions,
                                                                             List<CouponGroup> couponGroups) {
        return IntStream.range(0, promotionOptions.size())
                .mapToObj(index -> PromotionOptionResponse.of(promotionOptions.get(index), couponGroups.get(index)))
                .collect(Collectors.toList());
    }

    public record PromotionOptionResponse(

            String member,
            Instant lastOrderAt,
            Boolean isRandom,
            CouponGroupResponse couponGroup
    ) {
        public static PromotionOptionResponse of(PromotionOption promotionOption, CouponGroup couponGroup) {
            return new PromotionOptionResponse(
                    promotionOption.getMemberType().name(),
                    promotionOption.getLastOrderAt(),
                    promotionOption.getLastOrderBefore(),
                    CouponGroupResponse.from(couponGroup)
            );
        }
    }

    public record CouponGroupResponse(

            Long id,
            String title
    ) {
        public static CouponGroupResponse from(CouponGroup couponGroup) {
            return new CouponGroupResponse(
                    couponGroup.getId(),
                    couponGroup.getTitle()
            );
        }
    }

}
