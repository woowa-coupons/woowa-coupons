package woowa.promotion.application.app.promotion.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import woowa.promotion.app.promotion.application.AppPromotionService;
import woowa.promotion.util.ApplicationTest;
import woowa.promotion.util.fixture.FixtureFactory;
import woowa.promotion.util.fixture.PromotionFixture;

@DisplayName("[비즈니스 로직 테스트][회원] 프로모션")
class AppPromotionServiceTest extends ApplicationTest {

    @Autowired
    private AppPromotionService appPromotionService;

    @DisplayName("회원이 프로모션 조회시 노출여부가 true인 프로모션만 조회된다.")
    @Test
    void retrieveIsDisplayEqualsTruePromotions() {
        // given
        supportRepository.save(FixtureFactory.createPromotion(PromotionFixture.A_프로모션));
        supportRepository.save(FixtureFactory.createPromotion(PromotionFixture.B_프로모션));
        supportRepository.save(FixtureFactory.createPromotion(PromotionFixture.C_프로모션));    // is_display = false

        // when
        var response = appPromotionService.retrievePromotions();

        // then
        assertAll(
                () -> assertThat(response).hasSize(2),
                () -> assertThat(response.get(0))
                        .hasFieldOrProperty("id")
                        .hasFieldOrProperty("bannerUrl")
                        .hasFieldOrProperty("promotionPageUrl")
        );
    }

}
