package woowa.promotion.acceptance;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import woowa.promotion.fixture.FixtureFactory;
import woowa.promotion.util.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

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
}
