package woowa.promotion.app.member.presentation.dto.request;

import woowa.promotion.app.member.application.dto.request.SignUpServiceRequest;

public record SignUpRequest(
        String email,
        String nickname,
        String password
) {
    public SignUpServiceRequest toServiceRequest() {
        return new SignUpServiceRequest(email, nickname, password);
    }
}
