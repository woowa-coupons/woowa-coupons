package woowa.promotion.admin.admin.application.dto.request;

import woowa.promotion.admin.admin.presentation.dto.request.SignInRequest;

public record SignInServiceRequest(
        String email,
        String password
) {
    public static SignInServiceRequest from(SignInRequest request) {
        return new SignInServiceRequest(
                request.email(),
                request.password()
        );
    }
}
