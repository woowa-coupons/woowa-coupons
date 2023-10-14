package woowa.promotion.app.member.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import woowa.promotion.app.member.application.MemberService;
import woowa.promotion.app.member.presentation.dto.request.SignInRequest;
import woowa.promotion.app.member.presentation.dto.request.SignUpRequest;
import woowa.promotion.app.member.presentation.dto.response.SignInResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/auth")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signup(
            @RequestBody SignUpRequest signupRequest
    ) throws Exception {
        memberService.signUp(signupRequest.toServiceRequest());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signin(
            @RequestBody SignInRequest signInRequest
    ) throws Exception {
        String accessToken = memberService.signIn(signInRequest.toServiceRequest());
        return ResponseEntity.ok().body(new SignInResponse(accessToken));
    }
}