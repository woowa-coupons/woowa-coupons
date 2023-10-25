package woowa.promotion.acceptance;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import woowa.promotion.admin.admin.domain.Admin;
import woowa.promotion.fixture.FixtureFactory;
import woowa.promotion.util.AcceptanceTest;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static woowa.promotion.fixture.CouponGroupFixture.A_쿠폰그룹;
import static woowa.promotion.fixture.CouponGroupFixture.B_쿠폰그룹;
import static woowa.promotion.fixture.FixtureFactory.*;
import static woowa.promotion.fixture.PromotionFixture.A_프로모션;
import static woowa.promotion.fixture.PromotionFixture.B_프로모션;
import static woowa.promotion.fixture.PromotionOptionFixture.A_프로모션_옵션;
import static woowa.promotion.fixture.PromotionOptionFixture.B_프로모션_옵션;

@DisplayName("[인수테스트] 관리자 - 프로모션")
public class PromotionAcceptanceTest extends AcceptanceTest {

    @DisplayName("프로모션 등록에 성공한다.")
    @Test
    void register() {
        // given
        Admin admin = supportRepository.save(Admin.of("nickname", "email", "password"));
        String accessToken = jwtProvider.createAccessToken(Map.of("adminId", admin.getId()));

        var couponGroupA = makeCouponGroup(createCouponGroup(A_쿠폰그룹));
        var couponGroupB = makeCouponGroup(createCouponGroup(B_쿠폰그룹));
        var promotionOptionRequestA = createPromotionOptionRequest(A_프로모션_옵션, couponGroupA.getId());
        var promotionOptionRequestB = createPromotionOptionRequest(B_프로모션_옵션, couponGroupB.getId());

        var request = RestAssured
                .given().log().all()
                .auth().oauth2(accessToken)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(FixtureFactory.createPromotionRegisterRequest(A_프로모션,
                        List.of(promotionOptionRequestA, promotionOptionRequestB)));

        // when
        var response = request
                .when().post("/admin/promotions")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @DisplayName("프로모션 리스트를 조회한다.")
    @Test
    void getPromotionList() {
        // given
        Admin admin = supportRepository.save(Admin.of("nickname", "email", "password"));
        String accessToken = jwtProvider.createAccessToken(Map.of("adminId", admin.getId()));
        
        makePromotion(createPromotion(A_프로모션));
        makePromotion(createPromotion(B_프로모션));
        var request = RestAssured
                .given().log().all()
                .auth().oauth2(accessToken)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        // when
        var response = request
                .when().get("/admin/promotions")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.body().jsonPath().getString("contents[0].title"));
    }

}
