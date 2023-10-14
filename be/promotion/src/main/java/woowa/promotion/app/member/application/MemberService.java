package woowa.promotion.app.member.application;

import static woowa.promotion.global.exception.ErrorCode.INVALID_PASSWORD;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import woowa.promotion.app.member.application.dto.request.SignInServiceRequest;
import woowa.promotion.app.member.application.dto.request.SignUpServiceRequest;
import woowa.promotion.app.member.application.dto.response.SignInServiceResponse;
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

    public void signUp(SignUpServiceRequest request) throws Exception {
        String nickname = request.nickname();
        String email = request.email();
        String encryptedPassword = passwordEncoder.encrypt(request.password());

        Member member = new Member(nickname, email, encryptedPassword);

        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public SignInServiceResponse signIn(SignInServiceRequest request) throws Exception {
        Member findUser = findByEmail(request.email());

        if (!findUser.isSamePassword(passwordEncoder.encrypt(request.password()))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, INVALID_PASSWORD.getContent());
        }

        String accessToken = jwtProvider.createAccessTokenByMemberId(findUser.getId());
        return new SignInServiceResponse(accessToken);
    }

    private Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_EMAIL.getContent()));
    }

}
