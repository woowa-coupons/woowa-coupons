package woowa.promotion.app.member.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import woowa.promotion.app.member.application.dto.request.SignInServiceRequest;
import woowa.promotion.app.member.application.dto.request.SignUpServiceRequest;
import woowa.promotion.app.member.application.dto.response.SignInServiceResponse;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.app.member.infrastructure.MemberRepository;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.exception.domain.MemberException;
import woowa.promotion.global.security.hash.PasswordEncoder;
import woowa.promotion.util.ApplicationTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static woowa.promotion.fixture.UserFixture.유저_June;

@DisplayName("[비즈니스 로직 테스트] 회원")
class MemberServiceTest extends ApplicationTest {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void signup() {
        // given
        String nickname = 유저_June.getNickname();
        String email = 유저_June.getEmail();
        String password = 유저_June.getNickname();
        SignUpServiceRequest signupServiceRequest = new SignUpServiceRequest(email, nickname, password);

        // when
        memberService.signUp(signupServiceRequest);

        // then
        Member june = memberRepository.findByNickname(유저_June.getNickname()).orElseThrow();
        String encryptedPassword = passwordEncoder.encrypt(password);

        assertThat(june)
                .extracting("nickname", "email", "password")
                .containsExactly(nickname, email, encryptedPassword);
    }

    @Test
    void signin() {
        // given
        Member june = makeJune();
        SignInServiceRequest request = new SignInServiceRequest(june.getEmail(), "password");

        // when
        SignInServiceResponse response = memberService.signIn(request);

        // then
        assertThat(response.accessToken()).isNotNull();
    }

    @Test
    void signinFailedWithWrongPassword() {
        // given
        Member june = makeJune();
        String wrongpassword = "wrong";
        SignInServiceRequest request = new SignInServiceRequest(june.getEmail(), wrongpassword);

        // when
        assertThatThrownBy(
                () -> memberService.signIn(request))
                .isInstanceOf(ApiException.class)
                .extracting("status", "message")
                .containsExactly(
                        MemberException.INVALID_PASSWORD.getHttpStatus().value(),
                        MemberException.INVALID_PASSWORD.getMessage()
                );
    }

    private Member makeJune() {
        String nickname = 유저_June.getNickname();
        String email = 유저_June.getEmail();
        String password = 유저_June.getPassword();
        Member member = new Member(nickname, email, passwordEncoder.encrypt(password));

        return memberRepository.save(member);
    }
}
