package woowa.promotion.admin.admin.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import woowa.promotion.admin.admin.application.dto.request.SignInServiceRequest;
import woowa.promotion.admin.admin.application.dto.request.SignupServiceRequest;
import woowa.promotion.admin.admin.application.dto.response.SignInServiceResponse;
import woowa.promotion.admin.admin.domain.Admin;
import woowa.promotion.fixture.FixtureFactory;
import woowa.promotion.global.security.hash.PasswordEncoder;
import woowa.promotion.util.ApplicationTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("[비즈니스 로직 테스트] 관리자")
class AuthServiceTest extends ApplicationTest {

    @Autowired
    private AuthService authService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @DisplayName("관리자 회원가입에 성공하며 암호화된 비밀번호가 저장된다.")
    @Test
    void signup() {
        // given
        SignupServiceRequest request = FixtureFactory.createSignupServiceRequest();

        // when
        authService.signup(request);

        String encrypted = passwordEncoder.encrypt(request.password());

        // then
        Admin admin = supportRepository.findAll(Admin.class).get(0);

        assertAll(
                () -> assertThat(admin).isNotNull(),
                () -> assertThat(admin.getPassword()).isEqualTo(encrypted)
        );
    }

    @DisplayName("관리자가 로그인에 성공하며 액세스 토큰을 발급받는다.")
    @Test
    void signIn() {
        // given
        SignInServiceRequest request = FixtureFactory.createSignInServiceRequest();
        supportRepository.save(FixtureFactory.createAdmin());

        // when
        SignInServiceResponse response = authService.signIn(request);

        // then
        assertThat(response.accessToken()).isNotBlank();
    }
}
