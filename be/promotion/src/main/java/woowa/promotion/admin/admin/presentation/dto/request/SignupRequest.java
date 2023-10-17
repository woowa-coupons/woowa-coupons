package woowa.promotion.admin.admin.presentation.dto.request;

public record SignupRequest(
        String email,
        String nickname,
        String password
) {
}
