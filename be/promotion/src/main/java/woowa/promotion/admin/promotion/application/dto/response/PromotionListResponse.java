package woowa.promotion.admin.promotion.application.dto.response;

import java.time.Instant;
import woowa.promotion.admin.promotion.domain.Promotion;

public record PromotionListResponse(
        Long id,
        String bannerUrl,
        String title,
        Instant startedAt,
        Instant finishedAt,
        String progressStatus
) {
    public static PromotionListResponse from(Promotion promotion) {
        return new PromotionListResponse(promotion.getId(),
                promotion.getBannerUrl(),
                promotion.getTitle(),
                promotion.getStartedAt(),
                promotion.getFinishedAt(),
                promotion.getProgressStatus().name());
    }
}
