package woowa.promotion.admin.admin.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import woowa.promotion.admin.admin.application.AuthService;
import woowa.promotion.admin.admin.application.dto.request.SignInServiceRequest;
import woowa.promotion.admin.admin.application.dto.request.SignupServiceRequest;
import woowa.promotion.admin.admin.presentation.dto.request.SignInRequest;
import woowa.promotion.admin.admin.presentation.dto.request.SignupRequest;
import woowa.promotion.admin.admin.presentation.dto.response.SignInResponse;

@RequiredArgsConstructor
@RequestMapping("/admin/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signup(
            @RequestBody SignupRequest request
    ) {
        authService.signup(SignupServiceRequest.from(request));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(
            @RequestBody SignInRequest request
    ) {
        return ResponseEntity.ok()
                .body(SignInResponse.from(authService.signIn(SignInServiceRequest.from(request))));
    }

}
