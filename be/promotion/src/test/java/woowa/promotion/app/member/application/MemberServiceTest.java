package woowa.promotion.app.member.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import woowa.promotion.app.member.application.dto.request.SignInServiceRequest;
import woowa.promotion.app.member.application.dto.request.SignUpServiceRequest;
import woowa.promotion.app.member.application.dto.response.SignInServiceResponse;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.app.member.infrastructure.MemberRepository;
import woowa.promotion.app.member.security.hash.PasswordEncoder;
import woowa.promotion.global.exception.ErrorCode;
import woowa.promotion.util.ApplicationTest;

class MemberServiceTest extends ApplicationTest {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void signup() throws Exception {
        // given
        String nickname = "june";
        String email = "june@codesquad.kr";
        String password = "password";
        SignUpServiceRequest signupServiceRequest = new SignUpServiceRequest(nickname, email, password);

        // when
        memberService.signUp(signupServiceRequest);

        // then
        Member june = memberRepository.findByNickname("june").orElseThrow();
        String encryptedPassword = passwordEncoder.encrypt(password);

        assertThat(june)
                .extracting("nickname", "email", "password")
                .containsExactly(nickname, email, encryptedPassword);
    }

    @Test
    void signin() throws Exception {
        // given
        Member june = makeJune();
        SignInServiceRequest request = new SignInServiceRequest(june.getEmail(), "password");

        // when
        SignInServiceResponse response = memberService.signIn(request);

        // then
        assertThat(response.accessToken()).isNotNull();
    }

    @Test
    void signinFailedWithWrongPassword() throws Exception {
        // given
        Member june = makeJune();
        String wrongpassword = "wrong";
        SignInServiceRequest request = new SignInServiceRequest(june.getEmail(), wrongpassword);

        // when
        assertThatThrownBy(
                () -> memberService.signIn(request))
                .isInstanceOf(ResponseStatusException.class)
                .extracting("status", "reason")
                .containsExactly(
                        HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_PASSWORD.getContent()
                );
    }

    private Member makeJune() throws Exception {
        String nickname = "june";
        String email = "june@codesquad.kr";
        String password = "password";
        Member member = new Member(nickname, email, passwordEncoder.encrypt(password));

        return memberRepository.save(member);
    }
}