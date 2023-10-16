package woowa.promotion.admin.admin.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import woowa.promotion.admin.admin.application.AuthService;
import woowa.promotion.admin.admin.application.dto.SignupServiceRequest;
import woowa.promotion.admin.admin.presentation.dto.request.SignupRequest;

@RequiredArgsConstructor
@RequestMapping("/admin/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signup(@RequestBody SignupRequest request) {
        authService.signup(SignupServiceRequest.from(request));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
