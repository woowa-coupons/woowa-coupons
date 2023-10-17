package woowa.promotion.global.domain.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Component;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.exception.domain.AuthorizationException;
import woowa.promotion.global.properties.JwtProperties;

@Component
public class JwtProvider {

    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 1000L * 60 * 60 * 24;

    private final Key key;

    public JwtProvider(JwtProperties jwtProperties) {
        this.key = Keys.hmacShaKeyFor(jwtProperties.secretKey().getBytes());
    }

    public String createAccessToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(getExpireDate(ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public Date getExpireDate(long expirationTime) {
        return new Date(System.currentTimeMillis() + expirationTime);
    }

    public Claims extractClaims(String accessToken) {
        Jws<Claims> claimsJws = validateJws(accessToken);
        return claimsJws.getBody();
    }

    private Jws<Claims> validateJws(String accessToken) {
        try {
            return extractJws(accessToken);
        } catch (ExpiredJwtException e) {
            throw new ApiException(AuthorizationException.EXPIRED_TOKEN);
        } catch (JwtException e) {
            throw new ApiException(AuthorizationException.INVALID_TOKEN);
        }
    }

    private Jws<Claims> extractJws(String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken);
    }
}
