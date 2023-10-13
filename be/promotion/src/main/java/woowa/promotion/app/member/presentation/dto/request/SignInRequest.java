package woowa.promotion.app.member.presentation.dto.request;

import woowa.promotion.app.member.application.dto.request.SignInServiceRequest;

public record SignInRequest(
        String email,
        String password
) {
    public SignInServiceRequest toServiceRequest() {
        return new SignInServiceRequest(email, password);
    }
}
