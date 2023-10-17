package woowa.promotion.global.util;

import javax.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.exception.domain.AuthorizationException;
import woowa.promotion.global.exception.domain.JwtException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpAuthorizationUtil {

    private static final String TOKEN_PREFIX = "Bearer";
    private static final int BEARER_TOKEN_PREFIX_LENGTH = TOKEN_PREFIX.length() + 1;

    public static String extractAccessToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        validateBearerToken(authorizationHeader);
        try {
            return authorizationHeader.substring(BEARER_TOKEN_PREFIX_LENGTH);
        } catch (StringIndexOutOfBoundsException e) {
            throw new ApiException(JwtException.INVALID_FORMAT);
        }
    }

    private static void validateBearerToken(String authorizationHeader) {
        if (!containsBearerToken(authorizationHeader)) {
            throw new ApiException(AuthorizationException.EMPTY_TOKEN);
        }
    }

    private static boolean containsBearerToken(String authorizationHeader) {
        return authorizationHeader != null
                && authorizationHeader.startsWith(TOKEN_PREFIX);
    }
}
