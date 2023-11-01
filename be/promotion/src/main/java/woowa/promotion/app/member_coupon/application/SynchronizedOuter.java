package woowa.promotion.app.member_coupon.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.app.member_coupon.presentation.dto.CouponIssueRequest;

@RequiredArgsConstructor
@Service
public class SynchronizedOuter {

    private final MemberCouponService memberCouponService;

    public synchronized void issueCoupon(CouponIssueRequest request, Member member) {
        memberCouponService.issueCoupon(request, member);
    }
}
