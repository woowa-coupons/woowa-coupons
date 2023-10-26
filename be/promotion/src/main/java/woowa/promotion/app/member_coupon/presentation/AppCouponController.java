package woowa.promotion.app.member_coupon.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.app.member_coupon.application.MemberCouponService;
import woowa.promotion.app.member_coupon.presentation.dto.CouponIssueRequest;
import woowa.promotion.global.resolver.Authentication;

@RequiredArgsConstructor
@RequestMapping("/app/member-coupons")
@RestController
public class AppCouponController {

    private final MemberCouponService memberCouponService;

    @PostMapping
    public ResponseEntity<Void> issueCoupon(
            @RequestBody CouponIssueRequest request,
            @Authentication Member member
    ) {
        memberCouponService.issueCoupon(request, member);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
