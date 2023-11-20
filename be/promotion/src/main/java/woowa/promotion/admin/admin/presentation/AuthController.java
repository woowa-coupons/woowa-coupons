package woowa.promotion.admin.admin.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import woowa.promotion.admin.admin.application.AdminService;
import woowa.promotion.admin.admin.presentation.dto.request.SignInRequest;
import woowa.promotion.admin.admin.presentation.dto.request.SignUpRequest;
import woowa.promotion.admin.admin.presentation.dto.response.SignInResponse;

@RequiredArgsConstructor
@RequestMapping("/admin/auth")
@RestController
public class AuthController {

    private final AdminService adminService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(
                                        @RequestBody SignUpRequest request
    ) {
        adminService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(
            @RequestBody SignInRequest request
    ) {
        SignInResponse response = adminService.signIn(request);
        return ResponseEntity.ok()
                .body(response);
    }
}
