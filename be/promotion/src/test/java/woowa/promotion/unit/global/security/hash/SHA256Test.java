package woowa.promotion.unit.global.security.hash;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.security.hash.SHA256;

@DisplayName("[단위테스트] SHA256")
class SHA256Test {

    private SHA256 sha256 = new SHA256();

    @DisplayName("비밀번호 암호화에 성공한다.")
    @Test
    void encrypt() {
        // given
        String originPassword = "1234";

        // when
        String encryptedPassword = sha256.encrypt(originPassword);

        // then
        assertThat(encryptedPassword).isNotEqualTo("1234");
    }

    @DisplayName("비밀번호가 null로 들어오면 예외가 발생한다.")
    @Test
    void givenNullPassword_whenEncrypt_thenThrowsException() {
        // given
        String originPassword = null;

        // when & then
        assertThatThrownBy(() -> sha256.encrypt(originPassword))
                .isInstanceOf(ApiException.class);
    }

}
