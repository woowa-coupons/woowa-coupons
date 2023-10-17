package woowa.promotion.global.interceptor;

import static woowa.promotion.global.util.HttpAuthorizationUtil.extractAccessToken;

import io.jsonwebtoken.Claims;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import woowa.promotion.admin.admin.domain.Admin;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.global.domain.jwt.JwtProvider;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.exception.domain.AuthorizationException;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private static final String ADMIN_ID_KEY = "adminId";
    private static final String MEMBER_ID_KEY = "memberId";

    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (CorsUtils.isPreFlightRequest(request)) {
            return true;
        }

        String requestURI = request.getRequestURI();

        if (requestURI.startsWith("/admin")) {
            Long adminId = getTokenValue(request, ADMIN_ID_KEY);
            Admin admin = null; // TODO memberId로 어드민 유저 유효성 검사 진행
            request.setAttribute("admin", admin);
        }

        if (requestURI.startsWith("/app")) {
            Long memberId = getTokenValue(request, MEMBER_ID_KEY);
            Member member = null; // TODO memberId로 일반 회원 유효성 검사 진행
            request.setAttribute("member", member);
        }

        return true;
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
