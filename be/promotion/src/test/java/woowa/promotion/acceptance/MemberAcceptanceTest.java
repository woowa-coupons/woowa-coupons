package woowa.promotion.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import woowa.promotion.util.AcceptanceTest;

public class MemberAcceptanceTest extends AcceptanceTest {

    @Test
    void signUp() {
        // given
        var request = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(Map.of("email", "june@codesquad.kr", "nickname", "june", "password", "password"));

        // when
        var response = request
                .when()
                .post("/app/auth/sign-up")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void signIn() throws Exception {
        // given
        makeMember();

        var request = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(Map.of("email", "june@codesquad.kr", "password", "password"));

        // when
        var response = request
                .when()
                .post("/app/auth/sign-in")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

}
