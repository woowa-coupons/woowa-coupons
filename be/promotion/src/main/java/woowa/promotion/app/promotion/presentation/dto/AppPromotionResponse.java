package woowa.promotion.app.promotion.presentation.dto;

import woowa.promotion.admin.promotion.domain.Promotion;

public record AppPromotionResponse(

        Long id,
        String bannerUrl,
        String promotionPageUrl
) {

    public static AppPromotionResponse from(Promotion promotion) {
        return new AppPromotionResponse(
                promotion.getId(),
                promotion.getBannerUrl(),
                promotion.getPromotionPageUrl()
        );
    }

}
