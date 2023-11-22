package woowa.promotion.admin.auth.presentation.dto.request;

public record SignInRequest(
        String email,
        String password
) {
}
