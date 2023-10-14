package woowa.promotion.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Member
    INVALID_PASSWORD("비밀번호가 일치하지 않습니다."),
    INVALID_EMAIL("존재하지 않는 이메일입니다. "),

    // PasswordEncoder
    FAILED_ENCRYPTION("잘못된 비밀번호입니다.");

    private final String content;
}
