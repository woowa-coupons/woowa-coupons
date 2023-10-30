package woowa.promotion.application.admin.admin.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import woowa.promotion.admin.admin.application.AdminService;
import woowa.promotion.admin.admin.application.dto.request.SignInServiceRequest;
import woowa.promotion.admin.admin.application.dto.request.SignupServiceRequest;
import woowa.promotion.admin.admin.application.dto.response.SignInServiceResponse;
import woowa.promotion.admin.admin.domain.Admin;
import woowa.promotion.fixture.FixtureFactory;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.security.hash.PasswordEncoder;
import woowa.promotion.util.ApplicationTest;

@DisplayName("[비즈니스 로직 테스트] 관리자")
class AdminServiceTest extends ApplicationTest {

    @Autowired
    private AdminService adminService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @DisplayName("회원가입")
    @Nested
    class Signup {

        @DisplayName("관리자 회원가입에 성공하며 암호화된 비밀번호가 저장된다.")
        @Test
        void signup() {
            // given
            SignupServiceRequest request = FixtureFactory.createSignupServiceRequest();

            // when
            adminService.signup(request);

            String encrypted = passwordEncoder.encrypt(request.password());

            // then
            Admin admin = supportRepository.findAll(Admin.class).get(0);

            assertAll(
                    () -> assertThat(admin).isNotNull(),
                    () -> assertThat(admin.getPassword()).isEqualTo(encrypted)
            );
        }

        @DisplayName("중복된 회원가입 데이터가 주어지면 예외가 발생한다.")
        @Test
        void duplicatedSignupData_whenSignup_thenThrowsException() {
            // given
            SignupServiceRequest request = FixtureFactory.createSignupServiceRequest();
            supportRepository.save(FixtureFactory.createAdmin(passwordEncoder.encrypt(request.password())));

            // when & then
            assertThatThrownBy(() -> adminService.signup(request))
                    .isInstanceOf(ApiException.class);
        }
    }

    @DisplayName("로그인")
    @Nested
    class SignIn {

        @DisplayName("관리자가 로그인에 성공하며 액세스 토큰을 발급받는다.")
        @Test
        void signIn() {
            // given
            SignInServiceRequest request = FixtureFactory.createSignInServiceRequest();
            supportRepository.save(FixtureFactory.createAdmin(passwordEncoder.encrypt(request.password())));

            // when
            SignInServiceResponse response = adminService.signIn(request);

            // then
            assertThat(response.accessToken()).isNotBlank();
        }

        @DisplayName("유효하지 않은 로그인 데이터가 주어지면 예외가 발생한다.")
        @Test
        void givenInvalidSignInData_whenSignIn_thenThrowsException() {
            // given
            SignInServiceRequest request = FixtureFactory.createSignInServiceRequest();

            // when & then
            assertThatThrownBy(() -> adminService.signIn(request))
                    .isInstanceOf(ApiException.class);
        }
    }
}
