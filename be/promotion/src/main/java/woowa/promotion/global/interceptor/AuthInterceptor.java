package woowa.promotion.global.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import woowa.promotion.global.service.AuthService;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final AuthService authService;

    @Transactional(readOnly = true)
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (CorsUtils.isPreFlightRequest(request)) {
            return true;
        }

        String requestURI = request.getRequestURI();

        if (requestURI.startsWith("/admin")) {
            authService.setAdminAttribute(request);
            return true;
        }

        if (requestURI.startsWith("/app")) {
            authService.setMemberAttribute(request);
            return true;
        }

        return false;
    }
}
