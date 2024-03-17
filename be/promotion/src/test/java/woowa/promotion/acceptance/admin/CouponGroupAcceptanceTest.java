package woowa.promotion.acceptance.admin;

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
import woowa.promotion.admin.auth.domain.Admin;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.admin.coupon_group.presentation.dto.response.CouponGroupDetailResponse.CouponGroupCouponResponse;
import woowa.promotion.admin.coupon_group.presentation.dto.response.CouponGroupsResponse;
import woowa.promotion.global.domain.page.CustomPage;
import woowa.promotion.util.AcceptanceTest;
import woowa.promotion.util.fixture.FixtureFactory;

@DisplayName("[인수테스트][관리자] 쿠폰 그룹")
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
        saveCouponGroupsAndCoupons();
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

    @Test
    @DisplayName("쿠폰 그룹 목록을 조회한다. (쿼리 파라미터 없음)")
    void retrieveCouponGroupsWithNoQueryParams() {
        // given
        Admin admin = supportRepository.save(FixtureFactory.createAdmin(passwordEncoder.encrypt("1234")));
        saveCouponGroupsAndCoupons();
        String accessToken = jwtProvider.createAccessToken(Map.of("adminId", admin.getId()));

        var request = RestAssured
                .given().log().all()
                .auth().oauth2(accessToken);

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

    @DisplayName("쿠폰 그룹 간단 목록을 조회한다.")
    @Test
    void retrieveSimpleCouponGroup() {
        // given
        Admin admin = supportRepository.save(FixtureFactory.createAdmin(passwordEncoder.encrypt("1234")));
        saveCouponGroupsAndCoupons();
        String accessToken = jwtProvider.createAccessToken(Map.of("adminId", admin.getId()));

        // when
        var response = RestAssured
                .given().log().all()
                .auth().oauth2(accessToken)
                .get("/admin/coupon-groups/summary?size=15")
                .then().log().all()
                .extract();

        // then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.jsonPath().getList("couponGroups", CouponGroup.class)).hasSize(15),
                () -> assertThat(response.jsonPath().getBoolean("hasNext")).isFalse(),
                () -> assertThat(response.jsonPath().getObject("couponGroups[0]", CouponGroup.class))
                        .hasFieldOrProperty("id")
                        .hasFieldOrProperty("title")
        );
    }

    @DisplayName("쿠폰 그룹 상세 목록을 조회한다.")
    @Test
    void retrieveDetailCouponGroup() {
        // given
        Admin admin = supportRepository.save(FixtureFactory.createAdmin(passwordEncoder.encrypt("1234")));
        saveCouponGroupsAndCoupons();
        String accessToken = jwtProvider.createAccessToken(Map.of("adminId", admin.getId()));

        // when
        var response = RestAssured
                .given().log().all()
                .auth().oauth2(accessToken)
                .get("/admin/coupon-groups/1")
                .then().log().all()
                .extract();

        // then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.jsonPath().getString("title")).isNotBlank(),
                () -> assertThat(response.jsonPath().getString("startedAt")).isNotBlank(),
                () -> assertThat(response.jsonPath().getString("finishedAt")).isNotBlank(),
                () -> assertThat(response.jsonPath().getList("coupons", CouponGroupCouponResponse.class)).hasSize(3),
                () -> assertThat(response.jsonPath().getObject("coupons[0]", CouponGroupCouponResponse.class))
                        .hasFieldOrProperty("title")
                        .hasFieldOrProperty("type")
                        .hasFieldOrProperty("discount")
                        .hasFieldOrProperty("initialQuantity")
        );
    }

    private void saveCouponGroupsAndCoupons() {
        List<CouponGroup> couponGroups = new ArrayList<>();
        int couponGroupCount = 15;

        IntStream.rangeClosed(1, couponGroupCount)
                .forEach(i -> couponGroups.add(
                        supportRepository.save(FixtureFactory.createCouponGroup("cg - " + i)))
                );
        IntStream.rangeClosed(1, 45)
                .forEach(i -> supportRepository.save(
                        FixtureFactory.createCoupon("coupon - " + i, couponGroups.get(i % couponGroupCount))));
    }
}
