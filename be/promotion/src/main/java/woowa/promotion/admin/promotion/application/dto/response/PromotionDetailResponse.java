package woowa.promotion.admin.promotion.application.dto.response;

import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.admin.promotion.domain.Promotion;
import woowa.promotion.admin.promotion_option.domain.PromotionOption;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record PromotionDetailResponse(

        String title,
        String content,
        String bannerUrl,
        Instant startedAt,
        Instant finishedAt,
        boolean isDisplay,
        String progressStatus,
        String promotionPageUrl,
        List<PromotionOptionResponse> promotionOptions
) {

    public PromotionDetailResponse(
            Promotion promotion,
            List<PromotionOption> promotionOption,
            List<CouponGroup> couponGroups
    ) {
        this(
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
                .mapToObj(index -> new PromotionOptionResponse(promotionOptions.get(index), couponGroups.get(index)))
                .collect(Collectors.toList());
    }

    public record PromotionOptionResponse(

            String member,
            Instant lastOrderAt,
            Boolean isRandom,
            CouponGroupResponse couponGroup
    ) {
        public PromotionOptionResponse(PromotionOption promotionOption, CouponGroup couponGroup) {
            this(
                    promotionOption.getMemberType().name(),
                    promotionOption.getLastOrderAt(),
                    couponGroup.getIsRandom(),
                    new CouponGroupResponse(couponGroup)
            );
        }
    }

    public record CouponGroupResponse(

            Long id,
            String title
    ) {
        public CouponGroupResponse(CouponGroup couponGroup) {
            this(couponGroup.getId(), couponGroup.getTitle());
        }
    }

}
