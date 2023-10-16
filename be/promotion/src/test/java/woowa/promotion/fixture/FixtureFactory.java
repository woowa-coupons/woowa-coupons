package woowa.promotion.fixture;

import woowa.promotion.admin.admin.application.dto.SignupServiceRequest;
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

}
