package woowa.promotion.admin.promotion.application.dto.request;

import java.time.Instant;
import java.util.List;
import woowa.promotion.admin.promotion.domain.ProgressStatus;
import woowa.promotion.admin.promotion.domain.Promotion;
import woowa.promotion.admin.promotion_option.domain.MemberType;
import woowa.promotion.admin.promotion_option.domain.PromotionOption;

public record PromotionRegisterRequest(
        String title,
        String content,
        String banner,
        Instant startedAt,
        Instant finishedAt,
        String promotionPageUrl,
        boolean isDisplay,
        String progressStatus,
        List<PromotionOptionRequest> promotionOptions
) {
    public Promotion toEntity() {
        return new Promotion(title, content, banner, startedAt, finishedAt, ProgressStatus.valueOf(progressStatus),
                promotionPageUrl,
                isDisplay);
    }

    public record PromotionOptionRequest(
            String memberType,
            Instant lastOrderAt,
            Boolean isRandom,
            Long couponGroupId
    ) {
        public PromotionOption toEntity(Promotion promotion) {
            return new PromotionOption(lastOrderAt, isRandom, promotion, MemberType.from(memberType));
        }

    }
}
