package woowa.promotion.admin.admin.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowa.promotion.admin.admin.application.dto.request.SignupServiceRequest;
import woowa.promotion.admin.admin.domain.Admin;
import woowa.promotion.admin.admin.infrastructure.AdminRepository;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.exception.domain.AdminException;
import woowa.promotion.global.security.hash.PasswordEncoder;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupServiceRequest request) {
        String encryptedPassword = passwordEncoder.encrypt(request.password());

        if (adminRepository.existsByEmail(request.email())) {
            throw new ApiException(AdminException.DUPLICATED_EMAIL);
        }

        Admin admin = Admin.of(request.email(), request.nickname(), encryptedPassword);
        adminRepository.save(admin);
    }

}
