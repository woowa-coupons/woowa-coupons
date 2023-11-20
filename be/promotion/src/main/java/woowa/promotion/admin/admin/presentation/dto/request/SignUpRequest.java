package woowa.promotion.admin.admin.presentation.dto.request;

public record SignUpRequest(
        String email,
        String nickname,
        String password
) {
}
