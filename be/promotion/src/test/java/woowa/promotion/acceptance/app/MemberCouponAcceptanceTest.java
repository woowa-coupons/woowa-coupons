package woowa.promotion.acceptance.app;

import static org.assertj.core.api.Assertions.assertThat;
import static woowa.promotion.util.fixture.CouponFixture.추석_쿠폰_랜덤_1000;
import static woowa.promotion.util.fixture.CouponFixture.추석_쿠폰_랜덤_2000;
import static woowa.promotion.util.fixture.CouponFixture.추석_쿠폰_랜덤_3000;
import static woowa.promotion.util.fixture.CouponFixture.추석_쿠폰_신규;
import static woowa.promotion.util.fixture.CouponGroupFixture.추석_쿠폰그룹_기존;
import static woowa.promotion.util.fixture.CouponGroupFixture.추석_쿠폰그룹_랜덤;
import static woowa.promotion.util.fixture.FixtureFactory.createCoupon;
import static woowa.promotion.util.fixture.FixtureFactory.createCouponGroup;
import static woowa.promotion.util.fixture.FixtureFactory.createPromotion;
import static woowa.promotion.util.fixture.FixtureFactory.createPromotionOption;
import static woowa.promotion.util.fixture.PromotionFixture.추석_프로모션;
import static woowa.promotion.util.fixture.PromotionOptionFixture.네번째_프로모션_옵션;
import static woowa.promotion.util.fixture.PromotionOptionFixture.두번째_프로모션_옵션;
import static woowa.promotion.util.fixture.PromotionOptionFixture.세번째_프로모션_옵션;
import static woowa.promotion.util.fixture.PromotionOptionFixture.첫번째_프로모션_옵션;
import static woowa.promotion.util.fixture.UserFixture.유저_Bruni;
import static woowa.promotion.util.fixture.UserFixture.유저_Jinny;
import static woowa.promotion.util.fixture.UserFixture.유저_June;

import io.restassured.RestAssured;
import java.util.Map;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import woowa.promotion.admin.promotion.domain.Promotion;
import woowa.promotion.admin.promotion_option.domain.PromotionOption;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.app.order.domain.Order;
import woowa.promotion.util.AcceptanceTest;
import woowa.promotion.util.fixture.FixtureFactory;

@DisplayName("[인수테스트][회원] 쿠폰 발급")
public class MemberCouponAcceptanceTest extends AcceptanceTest {

    @Disabled
    @DisplayName("시나리오 A: "
            + "프로모션 옵션 : 2023년 10월 26일 16시 전에 주문한 사람"
            + "쿠폰 옵션 :  랜덤이 아니다."
            + "프로모션에 마지막 주문일이 2023년 10월 26일 16시 이전인 회원이 쿠폰 발급에 성공한다.")
    @Test
    @Sql(value = {"classpath:saveScenarioA.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"classpath:schema.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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

    @DisplayName("시나리오 B: "
            + "프로모션 옵션 : 2023년 10월 26일 16시 후에 주문한 사람"
            + "쿠폰 옵션 :  랜덤이 아니다."
            + "프로모션에 마지막 주문일이 2023년 10월 26일 16시 이후인 회원이 쿠폰 발급에 성공한다.")
    @Test
    @Sql(value = {"classpath:saveScenarioB.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"classpath:schema.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void scenarioB() {
        // given
        Member member = makeMember(유저_Jinny);
        String accessToken = jwtProvider.createAccessToken(Map.of("memberId", member.getId()));
        Long promotionId = saveScenarioB();

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

    @DisplayName("시나리오 C: "
            + "프로모션 옵션 : 기존회원인 사람"
            + "쿠폰 옵션 : 랜덤이 아니다."
            + "프로모션에 마지막 주문일에 상관없이 기존 회원이 쿠폰 발급에 성공한다.")
    @Sql(value = "classpath:schema.sql")
    @Test
    void scenarioC() {
        // given
        Member member = makeMember(유저_Jinny);
        String accessToken = jwtProvider.createAccessToken(Map.of("memberId", member.getId()));
        supportRepository.save(new Order(member));
        Long promotionId = saveScenarioC();

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

    @DisplayName("시나리오 D: "
            + "프로모션 옵션 : 신규회원인 사람"
            + "쿠폰 옵션 : 랜덤이 아니다."
            + "프로모션에 마지막 주문일에 상관없이 신규 회원이 쿠폰 발급에 성공한다.")
    @Test
    void scenarioD() {
        // given
        Member member = makeMember(유저_June);
        String accessToken = jwtProvider.createAccessToken(Map.of("memberId", member.getId()));
        Long promotionId = saveScenarioD();

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

    @DisplayName("시나리오 E: "
            + "프로모션 옵션 : 신규 회원"
            + "쿠폰 옵션 :  랜덤"
            + "주문 내역이 없는 회원이 쿠폰 발급에 성공한다.")
    @Test
    void scenarioE() {
        // given
        Member member = makeMember(유저_June);
        String accessToken = jwtProvider.createAccessToken(Map.of("memberId", member.getId()));
        Long promotionId = saveScenarioE();

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

    private Long saveScenarioB() {
        var couponGroup = makeCouponGroup(createCouponGroup(추석_쿠폰그룹_기존));
        makeCoupon(createCoupon(추석_쿠폰_신규, couponGroup));
        Promotion promotion = makePromotion(createPromotion(추석_프로모션));
        PromotionOption promotionOption = makePromotionOption(createPromotionOption(두번째_프로모션_옵션, promotion));
        makePromotionOptionCouponGroup(promotionOption, couponGroup);
        return promotion.getId();
    }

    private Long saveScenarioC() {
        var couponGroup = makeCouponGroup(createCouponGroup(추석_쿠폰그룹_기존));
        makeCoupon(createCoupon(추석_쿠폰_신규, couponGroup));
        Promotion promotion = makePromotion(createPromotion(추석_프로모션));
        var promotionOption = makePromotionOption(createPromotionOption(세번째_프로모션_옵션, promotion));
        makePromotionOptionCouponGroup(promotionOption, couponGroup);
        return promotion.getId();
    }

    private Long saveScenarioD() {
        var couponGroup = makeCouponGroup(createCouponGroup(추석_쿠폰그룹_기존));
        makeCoupon(createCoupon(추석_쿠폰_신규, couponGroup));
        Promotion promotion = makePromotion(createPromotion(추석_프로모션));
        var promotionOption = makePromotionOption(createPromotionOption(네번째_프로모션_옵션, promotion));
        makePromotionOptionCouponGroup(promotionOption, couponGroup);
        return promotion.getId();
    }

    private Long saveScenarioE() {
        var couponGroup = makeCouponGroup(createCouponGroup(추석_쿠폰그룹_랜덤));
        makeCoupon(createCoupon(추석_쿠폰_랜덤_1000, couponGroup));
        makeCoupon(createCoupon(추석_쿠폰_랜덤_2000, couponGroup));
        makeCoupon(createCoupon(추석_쿠폰_랜덤_3000, couponGroup));
        Promotion promotion = makePromotion(createPromotion(추석_프로모션));
        PromotionOption promotionOption = makePromotionOption(createPromotionOption(네번째_프로모션_옵션, promotion));
        makePromotionOptionCouponGroup(promotionOption, couponGroup);
        return promotion.getId();
    }
}
