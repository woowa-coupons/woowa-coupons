package woowa.promotion.global.domain.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Component;
import woowa.promotion.global.properties.JwtProperties;

@Component
public class JwtProvider {

    public static final long ACCESS_TOKEN_EXPIRATION_TIME = 1000L * 60 * 60 * 24;
    public static final String MEMBER_ID = "memberId";

    private final JwtProperties jwtProperties;
    private final Key key;

    public JwtProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.key = Keys.hmacShaKeyFor(jwtProperties.secretKey().getBytes());
    }

    public String createAccessToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(getExpireDate(ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String createAccessTokenByMemberId(Long memberId) {
        Map<String, Object> claims = Map.of(MEMBER_ID, memberId);
        return createAccessToken(claims);
    }

    public Date getExpireDate(long expirationTime) {
        return new Date(System.currentTimeMillis() + expirationTime);
    }

}
