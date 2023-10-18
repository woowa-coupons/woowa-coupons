package woowa.promotion.fixture;

import woowa.promotion.admin.admin.application.dto.request.SignInServiceRequest;
import woowa.promotion.admin.admin.application.dto.request.SignupServiceRequest;
import woowa.promotion.admin.admin.application.dto.response.SignInServiceResponse;
import woowa.promotion.admin.admin.domain.Admin;
import woowa.promotion.admin.admin.presentation.dto.request.SignInRequest;
import woowa.promotion.admin.admin.presentation.dto.request.SignupRequest;
import woowa.promotion.app.member.domain.Member;

public class FixtureFactory {

    public static Member createMember(UserFixture userFixture, String password) {
        return new Member(userFixture.getNickname(), userFixture.getEmail(), password);
    }

    public static SignupRequest createSignupRequest() {
        return new SignupRequest(
                "bruni@woowa.com",
                "브루니",
                "1234"
        );
    }

    public static SignupServiceRequest createSignupServiceRequest() {
        return new SignupServiceRequest(
                "bruni@woowa.com",
                "브루니",
                "1234"
        );
    }

    public static SignInRequest createSignInRequest() {
        return new SignInRequest(
                "bruni@woowa.com",
                "1234"
        );
    }

    public static SignInServiceRequest createSignInServiceRequest() {
        return new SignInServiceRequest(
                "bruni@woowa.com",
                "1234"
        );
    }

    public static SignInServiceResponse createSignInServiceResponse() {
        return new SignInServiceResponse("accessToken");
    }

    public static Admin createAdmin() {
        return Admin.of("브루니", "bruni@woowa.com", "1234");
    }
}
