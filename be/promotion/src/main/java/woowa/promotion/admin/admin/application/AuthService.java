package woowa.promotion.admin.admin.application;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowa.promotion.admin.admin.application.dto.request.SignInServiceRequest;
import woowa.promotion.admin.admin.application.dto.request.SignupServiceRequest;
import woowa.promotion.admin.admin.application.dto.response.SignInServiceResponse;
import woowa.promotion.admin.admin.domain.Admin;
import woowa.promotion.admin.admin.infrastructure.AdminRepository;
import woowa.promotion.global.domain.jwt.JwtProvider;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.exception.domain.AdminException;
import woowa.promotion.global.security.hash.PasswordEncoder;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public void signup(SignupServiceRequest request) {
        String encryptedPassword = passwordEncoder.encrypt(request.password());

        if (adminRepository.existsByEmail(request.email())) {
            throw new ApiException(AdminException.DUPLICATED_EMAIL);
        }

        Admin admin = Admin.of(request.nickname(), request.email(), encryptedPassword);
        adminRepository.save(admin);
    }

    @Transactional(readOnly = true)
    public SignInServiceResponse signIn(SignInServiceRequest request) {
        Admin admin = adminRepository.findByEmail(request.email())
                .orElseThrow(() -> new ApiException(AdminException.INVALID_EMAIL));

        if (!admin.isSamePassword(passwordEncoder.encrypt(request.password()))) {
            throw new ApiException(AdminException.INVALID_PASSWORD);
        }

        String accessToken = jwtProvider.createAccessToken(Map.of("adminId", admin.getId()));
        return new SignInServiceResponse(accessToken);
    }
}
