package woowa.promotion.unit.global.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import woowa.promotion.global.util.HttpAuthorizationUtil;

@DisplayName("HttpAuthorizationUtil 단위 테스트")
class HttpAuthorizationUtilTest {

    @DisplayName("ServletRequest의 Authorization 헤더에서 액세스 토큰을 추출하는데 성공한다.")
    @Test
    void extractAccessToken() {
        // given
        var request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer accessToken");

        // when
        String accessToken = HttpAuthorizationUtil.extractAccessToken(request);

        // then
        assertThat(accessToken).isEqualTo("accessToken");
    }

}
