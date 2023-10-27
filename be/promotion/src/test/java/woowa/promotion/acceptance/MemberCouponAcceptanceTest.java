package woowa.promotion.acceptance;

import static org.assertj.core.api.Assertions.assertThat;
import static woowa.promotion.fixture.CouponFixture.추석_쿠폰_신규;
import static woowa.promotion.fixture.CouponGroupFixture.추석_쿠폰그룹_기존;
import static woowa.promotion.fixture.FixtureFactory.createCoupon;
import static woowa.promotion.fixture.FixtureFactory.createCouponGroup;
import static woowa.promotion.fixture.FixtureFactory.createPromotion;
import static woowa.promotion.fixture.FixtureFactory.createPromotionOption;
import static woowa.promotion.fixture.PromotionFixture.추석_프로모션;
import static woowa.promotion.fixture.PromotionOptionFixture.첫번째_프로모션_옵션;
import static woowa.promotion.fixture.UserFixture.유저_Bruni;

import io.restassured.RestAssured;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import woowa.promotion.admin.promotion.domain.Promotion;
import woowa.promotion.admin.promotion_option.domain.PromotionOption;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.fixture.FixtureFactory;
import woowa.promotion.util.AcceptanceTest;

@DisplayName("[인수테스트] 회원")
public class MemberCouponAcceptanceTest extends AcceptanceTest {

    @DisplayName("시나리오 A")
    @Test
    @Sql(value = {"classpath:scenarioA.sql"})
    void scenarioA() {
        // given
        Member member = makeMember(유저_Bruni);
        String accessToken = jwtProvider.createAccessToken(Map.of("memberId", member.getId()));
        Long promotionId = saveScenarioA();

        var request = RestAssured
                .given().log().all()
                .auth().oauth2(accessToken)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(FixtureFactory.createCouponIssueRequest(promotionId));

        // when
        var response = request
                .when().post("/app/member-coupons")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }

    private Long saveScenarioA() {
        var couponGroup = makeCouponGroup(createCouponGroup(추석_쿠폰그룹_기존));
        makeCoupon(createCoupon(추석_쿠폰_신규, couponGroup));
        Promotion promotion = makePromotion(createPromotion(추석_프로모션));
        PromotionOption promotionOption = makePromotionOption(createPromotionOption(첫번째_프로모션_옵션, promotion));
        makePromotionOptionCouponGroup(promotionOption, couponGroup);
        return promotion.getId();
    }

}
