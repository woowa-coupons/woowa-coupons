package woowa.promotion.acceptance.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import io.restassured.RestAssured;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import woowa.promotion.app.promotion.presentation.dto.AppPromotionResponse;
import woowa.promotion.util.AcceptanceTest;
import woowa.promotion.util.fixture.FixtureFactory;
import woowa.promotion.util.fixture.MemberFixture;
import woowa.promotion.util.fixture.PromotionFixture;

@DisplayName("[인수테스트][회원] 프로모션")
public class AppPromotionAcceptanceTest extends AcceptanceTest {

    @DisplayName("회원이 프로모션 조회시 노출여부가 true인 프로모션만 조회된다.")
    @Test
    void retrievePromotions() {
        // given
        supportRepository.save(FixtureFactory.createMember(MemberFixture.유저_June, passwordEncoder.encrypt("1234")));

        supportRepository.save(FixtureFactory.createPromotion(PromotionFixture.A_프로모션));
        supportRepository.save(FixtureFactory.createPromotion(PromotionFixture.B_프로모션));
        supportRepository.save(FixtureFactory.createPromotion(PromotionFixture.C_프로모션));

        var request = RestAssured
                .given().log().all()
                .auth().oauth2(jwtProvider.createAccessToken(Map.of("memberId", 1L)));

        // when
        var response = request
                .when()
                .get("/app/promotions")
                .then().log().all()
                .extract();

        // then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.jsonPath().getList(".", AppPromotionResponse.class)).hasSize(2),
                () -> assertThat(response.jsonPath().getObject("[0]", AppPromotionResponse.class))
                        .hasFieldOrProperty("id")
                        .hasFieldOrProperty("bannerUrl")
                        .hasFieldOrProperty("promotionPageUrl")
        );
    }

}
