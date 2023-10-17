package woowa.promotion.global.util;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.exception.domain.JwtException;

public class HttpAuthorizationUtil {

    private static final String TOKEN_PREFIX = "Bearer";
    private static final int BEARER_TOKEN_PREFIX_LENGTH = TOKEN_PREFIX.length() + 1;

    public static boolean containsBearerToken(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        return authorization != null && authorization.startsWith(TOKEN_PREFIX);
    }

    public static String extractAccessToken(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        try {
            return authorizationHeader.substring(BEARER_TOKEN_PREFIX_LENGTH);
        } catch (StringIndexOutOfBoundsException e) {
            throw new ApiException(JwtException.INVALID_FORMAT);
        }
    }
}
