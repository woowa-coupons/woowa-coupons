package woowa.promotion.admin.auth.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import woowa.promotion.admin.auth.application.AdminAuthService;
import woowa.promotion.admin.auth.presentation.dto.request.SignInRequest;
import woowa.promotion.admin.auth.presentation.dto.request.SignUpRequest;
import woowa.promotion.admin.auth.presentation.dto.response.SignInResponse;

@RequiredArgsConstructor
@RequestMapping("/admin/auth")
@RestController
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(
            @RequestBody SignUpRequest request
    ) {
        adminAuthService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(
            @RequestBody SignInRequest request
    ) {
        SignInResponse response = adminAuthService.signIn(request);
        return ResponseEntity.ok()
                .body(response);
    }
}
