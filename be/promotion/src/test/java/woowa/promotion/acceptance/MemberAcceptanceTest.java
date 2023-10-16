package woowa.promotion.acceptance;

import static org.assertj.core.api.Assertions.assertThat;
import static woowa.promotion.fixture.UserFixture.유저_June;

import io.restassured.RestAssured;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import woowa.promotion.app.member.presentation.dto.request.SignInRequest;
import woowa.promotion.app.member.presentation.dto.request.SignUpRequest;
import woowa.promotion.util.AcceptanceTest;

public class MemberAcceptanceTest extends AcceptanceTest {

    @ParameterizedTest
    @MethodSource("providerSignupUser")
    void signUp(String email, String nickname, String password) {
        // given
        var request = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new SignUpRequest(email, nickname, password));

        // when
        var response = request
                .when()
                .post("/app/auth/sign-up")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @ParameterizedTest
    @MethodSource("providerSigninUser")
    void signIn(String email, String password) {
        // given
        makeMember(유저_June);

        var request = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new SignInRequest(email, password));

        // when
        var response = request
                .when()
                .post("/app/auth/sign-in")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private static Stream<Arguments> providerSignupUser() {
        return Stream.of(
                Arguments.of(
                        유저_June.getEmail(),
                        유저_June.getNickname(),
                        유저_June.getPassword()
                )
        );
    }

    private static Stream<Arguments> providerSigninUser() {
        return Stream.of(
                Arguments.of(
                        유저_June.getEmail(),
                        유저_June.getPassword()
                )
        );
    }

}
