package woowa.promotion.acceptance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import io.restassured.RestAssured;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import woowa.promotion.admin.admin.domain.Admin;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.admin.coupon_group.presentation.dto.response.CouponGroupsResponse;
import woowa.promotion.fixture.FixtureFactory;
import woowa.promotion.global.domain.page.CustomPage;
import woowa.promotion.util.AcceptanceTest;

@DisplayName("[인수테스트] 관리자 - 쿠폰 그룹")
public class CouponGroupAcceptanceTest extends AcceptanceTest {

    @DisplayName("쿠폰 그룹 생성에 성공한다.")
    @Test
    void createCouponGroups() {
        // given
        Admin admin = supportRepository.save(Admin.of("nickname", "email", "password"));
        String accessToken = jwtProvider.createAccessToken(Map.of("adminId", admin.getId()));

        var request = RestAssured
                .given().log().all()
                .auth().oauth2(accessToken)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(FixtureFactory.createCouponGroupCreateRequest());

        // when
        var response = request
                .when().post("/admin/coupon-groups")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @DisplayName("쿠폰 그룹 목록을 조회한다.")
    @Test
    void retrieveCouponGroups() {
        // given
        Admin admin = supportRepository.save(FixtureFactory.createAdmin(passwordEncoder.encrypt("1234")));
        List<CouponGroup> couponGroups = new ArrayList<>();
        IntStream.rangeClosed(1, 15)
                .forEach(i -> couponGroups.add(
                        supportRepository.save(FixtureFactory.createCouponGroup("cg - " + i)))
                );
        IntStream.rangeClosed(1, 45)
                .forEach(i -> supportRepository.save(
                        FixtureFactory.createCoupon("coupon - " + i, couponGroups.get(i % 15))));
        String accessToken = jwtProvider.createAccessToken(Map.of("adminId", admin.getId()));

        var request = RestAssured
                .given().log().all()
                .auth().oauth2(accessToken)
                .queryParam("page", 1)
                .queryParam("size", 10);

        // when
        var response = request
                .get("/admin/coupon-groups")
                .then().log().all()
                .extract();

        // then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.jsonPath().getObject("data[0]", CouponGroupsResponse.class))
                        .hasFieldOrPropertyWithValue("title", "cg - 15"),
                () -> assertThat(response.jsonPath().getObject("paging", CustomPage.Paging.class))
                        .hasFieldOrPropertyWithValue("currentPage", 1)
                        .hasFieldOrPropertyWithValue("totalPages", 2)
                        .hasFieldOrPropertyWithValue("totalElements", 15L)
                        .hasFieldOrPropertyWithValue("size", 10)
        );
    }
}
