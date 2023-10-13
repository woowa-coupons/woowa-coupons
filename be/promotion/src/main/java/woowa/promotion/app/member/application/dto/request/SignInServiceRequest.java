package woowa.promotion.app.member.application.dto.request;

public record SignInServiceRequest(
        String email,
        String password
) {
}
