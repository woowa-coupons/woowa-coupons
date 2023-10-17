package woowa.promotion.admin.admin.application.dto.request;

import woowa.promotion.admin.admin.presentation.dto.request.SignupRequest;

public record SignupServiceRequest(
        String email,
        String nickname,
        String password
) {

    public static SignupServiceRequest from(SignupRequest request) {
        return new SignupServiceRequest(
                request.email(),
                request.nickname(),
                request.password()
        );
    }

}
