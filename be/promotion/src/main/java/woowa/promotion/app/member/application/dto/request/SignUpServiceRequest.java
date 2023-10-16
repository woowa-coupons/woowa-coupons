package woowa.promotion.app.member.application.dto.request;

public record SignUpServiceRequest(
        String email,
        String nickname,
        String password
) {

}
