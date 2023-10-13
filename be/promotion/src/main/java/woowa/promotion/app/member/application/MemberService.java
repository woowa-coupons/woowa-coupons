package woowa.promotion.app.member.application;

import static woowa.promotion.global.exception.ErrorCode.INVALID_PASSWORD;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import woowa.promotion.app.member.application.dto.request.SignInServiceRequest;
import woowa.promotion.app.member.application.dto.request.SignUpServiceRequest;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.app.member.infrastructure.MemberRepository;
import woowa.promotion.app.member.security.hash.PasswordEncoder;
import woowa.promotion.global.domain.jwt.JwtProvider;
import woowa.promotion.global.exception.ErrorCode;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpServiceRequest signupServiceRequest) throws Exception {
        String nickname = signupServiceRequest.nickname();
        String email = signupServiceRequest.email();
        String encryptedPassword = passwordEncoder.encrypt(signupServiceRequest.password());

        Member member = new Member(nickname, email, encryptedPassword);

        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public String signIn(SignInServiceRequest signInServiceRequest) throws Exception {
        Member findUser = findByEmail(signInServiceRequest.email());
        System.out.println("service transction" + Thread.currentThread().getName());
        if (!findUser.isSamePassword(passwordEncoder.encrypt(signInServiceRequest.password()))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, INVALID_PASSWORD.getContent());
        }

        return jwtProvider.createAccessTokenByMemberId(findUser.getId());
    }

    private Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_EMAIL.getContent()));
    }

}
