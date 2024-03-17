package woowa.promotion.unit.global.domain.jwt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import woowa.promotion.global.domain.jwt.JwtProvider;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.properties.JwtProperties;

@DisplayName("[단위테스트] JwtProvider")
class JwtProviderTest {

    private static final String SECRET_KEY = "1231231231231231231231231231231231312312312312312312312312312312";

    private final JwtProvider jwtProvider = new JwtProvider(new JwtProperties(SECRET_KEY));

    @DisplayName("액세스 토큰을 생성하는데 성공한다.")
    @Test
    void createAccessToken() {
        // given
        Map<String, Object> claim = Map.of("memberId", 1L);

        // when
        String accessToken = jwtProvider.createAccessToken(claim);

        // then
        assertThat(accessToken).isNotBlank();
    }

    @DisplayName("액세스 토큰에서 클레임을 추출하는데 성공한다.")
    @Test
    void extractClaimsFromAccessToken() {
        // given
        Map<String, Object> claim = Map.of("memberId", 1L);
        String accessToken = jwtProvider.createAccessToken(claim);

        // when
        Claims claims = jwtProvider.extractClaims(accessToken);

        // then
        assertThat(Long.parseLong(claims.get("memberId").toString())).isEqualTo(1L);
    }

    @DisplayName("만료된 액세스 토큰이 주어지면 클레임 추출시 예외를 던진다.")
    @Test
    void validateAccessToken() {
        // given
        Map<String, Object> claim = Map.of("memberId", 1L);

        String expiredToken = Jwts.builder()
                .setClaims(claim)
                .setExpiration(new Date(System.currentTimeMillis() - 1))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();

        // when & then
        assertThatThrownBy(() -> jwtProvider.extractClaims(expiredToken))
                .isInstanceOf(ApiException.class);
    }

}
