package woowa.promotion.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import woowa.promotion.admin.admin.domain.Admin;
import woowa.promotion.fixture.FixtureFactory;
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
}
