package woowa.promotion.global.service;

import static woowa.promotion.global.util.HttpAuthorizationUtil.extractAccessToken;

import io.jsonwebtoken.Claims;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowa.promotion.admin.auth.domain.Admin;
import woowa.promotion.admin.auth.infrastructure.AdminRepository;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.app.member.infrastructure.MemberRepository;
import woowa.promotion.global.domain.jwt.JwtProvider;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.exception.domain.AuthorizationException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final String ADMIN_ID_KEY = "adminId";
    private static final String MEMBER_ID_KEY = "memberId";

    private final JwtProvider jwtProvider;
    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public void setAdminAttribute(HttpServletRequest request) {
        Long adminId = getTokenValue(request, ADMIN_ID_KEY);
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new ApiException(AuthorizationException.INVALID_TOKEN));
        request.setAttribute("admin", admin);
    }

    @Transactional(readOnly = true)
    public void setMemberAttribute(HttpServletRequest request) {
        Long memberId = getTokenValue(request, MEMBER_ID_KEY);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ApiException(AuthorizationException.INVALID_TOKEN));
        request.setAttribute("member", member);
    }

    private long getTokenValue(HttpServletRequest request, String key) {
        String token = extractAccessToken(request);
        Claims claims = jwtProvider.extractClaims(token);
        return Long.parseLong(
                Optional.ofNullable(claims.get(key).toString())
                        .orElseThrow(() -> new ApiException(AuthorizationException.INVALID_TOKEN))
        );
    }
}
