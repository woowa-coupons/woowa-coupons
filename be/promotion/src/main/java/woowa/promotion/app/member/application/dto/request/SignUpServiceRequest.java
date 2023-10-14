package woowa.promotion.app.member.application.dto.request;

import woowa.promotion.app.member.domain.Member;

public record SignUpServiceRequest(
        String nickname,
        String email,
        String password
) {
    public Member toEntity() {
        return new Member(nickname, email, password);
    }
    
}
