package woowa.promotion.admin.admin.presentation.dto.response;

import woowa.promotion.admin.admin.application.dto.response.SignInServiceResponse;

public record SignInResponse(
        String accessToken
) {
    public static SignInResponse from(SignInServiceResponse response) {
        return new SignInResponse(response.accessToken());
    }
}
