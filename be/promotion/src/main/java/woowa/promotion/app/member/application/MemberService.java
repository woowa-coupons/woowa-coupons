package woowa.promotion.app.member.application;


import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowa.promotion.app.member.application.dto.request.SignInServiceRequest;
import woowa.promotion.app.member.application.dto.request.SignUpServiceRequest;
import woowa.promotion.app.member.application.dto.response.SignInServiceResponse;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.app.member.infrastructure.MemberRepository;
import woowa.promotion.global.domain.jwt.JwtProvider;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.exception.domain.MemberException;
import woowa.promotion.global.security.hash.PasswordEncoder;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpServiceRequest request) {
        String nickname = request.nickname();
        String email = request.email();
        String encryptedPassword = passwordEncoder.encrypt(request.password());

        if (isDuplicatedEmail(email)) {
            throw new ApiException(MemberException.DUPLICATED_EMAIL);
        }
        Member member = new Member(nickname, email, encryptedPassword);

        memberRepository.save(member);
    }

    private boolean isDuplicatedEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public SignInServiceResponse signIn(SignInServiceRequest request) {
        Member findUser = findByEmail(request.email());

        if (!findUser.isSamePassword(passwordEncoder.encrypt(request.password()))) {
            throw new ApiException(MemberException.INVALID_PASSWORD);
        }

        String accessToken = jwtProvider.createAccessToken(Map.of("memberId", findUser.getId()));
        return new SignInServiceResponse(accessToken);
    }

    private Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ApiException(MemberException.INVALID_EMAIL));
    }

}
