package woowa.promotion.acceptance;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import woowa.promotion.fixture.FixtureFactory;
import woowa.promotion.util.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("[인수테스트] 관리자")
public class AdminAcceptanceTest extends AcceptanceTest {

    @DisplayName("관리자가 회원가입에 성공한다.")
    @Test
    void signup() {
        // given
        var request = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(FixtureFactory.createSignupRequest());

        // when
        var response = request
                .when().post("/admin/auth/sign-up")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @DisplayName("관리자가 로그인에 성공해 토큰을 발급받는다.")
    @Test
    void signIn() {
        // given
        supportRepository.save(FixtureFactory.createAdmin(passwordEncoder.encrypt("1234")));
        var request = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(FixtureFactory.createSignInRequest());

        // when
        var response = request
                .when().post("/admin/auth/sign-in")
                .then().log().all()
                .extract();

        // then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.jsonPath().getString("accessToken")).isNotBlank()
        );
    }

}
