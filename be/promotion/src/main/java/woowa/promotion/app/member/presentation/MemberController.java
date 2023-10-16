package woowa.promotion.app.member.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import woowa.promotion.app.member.application.MemberService;
import woowa.promotion.app.member.application.dto.response.SignInServiceResponse;
import woowa.promotion.app.member.presentation.dto.request.SignInRequest;
import woowa.promotion.app.member.presentation.dto.request.SignUpRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/auth")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signup(
            @RequestBody SignUpRequest signupRequest
    ) {
        memberService.signUp(signupRequest.toServiceRequest());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInServiceResponse> signin(
            @RequestBody SignInRequest signInRequest
    ) {
        SignInServiceResponse response = memberService.signIn(signInRequest.toServiceRequest());
        return ResponseEntity.ok().body(response);
    }
}
