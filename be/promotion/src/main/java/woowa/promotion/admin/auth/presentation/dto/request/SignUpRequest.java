package woowa.promotion.admin.auth.presentation.dto.request;

public record SignUpRequest(
        String email,
        String nickname,
        String password
) {
}
