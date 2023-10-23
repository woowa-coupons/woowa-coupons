package woowa.promotion.app.member_coupon.application;


import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowa.promotion.admin.coupon.domain.Coupon;
import woowa.promotion.admin.coupon.infrastructure.CouponRepository;
import woowa.promotion.admin.promotion_option.domain.MemberType;
import woowa.promotion.admin.promotion_option.domain.PromotionOption;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.app.member_coupon.domain.MemberCoupon;
import woowa.promotion.app.member_coupon.infrastructure.MemberCouponRepository;
import woowa.promotion.app.order.infrastructure.OrderRepository;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.exception.domain.CouponException;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberCouponService {

    private final MemberCouponRepository memberCouponRepository;
    private final OrderRepository orderRepository;
    private final CouponRepository couponRepository;


    private void issueRandomCoupon(Long couponGroupId, Member member) {
        List<Coupon> coupons = couponRepository.findByCouponGroupIdAndRemainQuantityIsGreaterThan(couponGroupId, 0);
        if (coupons.isEmpty()) {
            throw new ApiException(CouponException.EXHAUSTED);
        }
        Collections.shuffle(coupons);
        saveMemberCoupon(member, coupons.get(0));
    }

    private void saveMemberCoupon(Member member, Coupon coupon) {
        coupon.issue();
        MemberCoupon memberCoupon = new MemberCoupon(member, coupon);
        memberCouponRepository.save(memberCoupon);
    }

    private boolean isEligibleForPromotion(Long memberId, PromotionOption promotionOption) {
        if (promotionOption.getLastOrderAt() != null) {
            // TODO 회원 마지막 주문일 체크하는 로직 구현(현 기획상 마지막 주문일 이전인지 이후인지 체크하는 기능이 없음)
            return false;
        }

        MemberType memberType = findMemberType(memberId);
        if (promotionOption.getMemberType().equals(memberType)) {
            return true;
        }
        return false;
    }

    // OrderService가 없어서 임의로 로직 생성
    private MemberType findMemberType(Long memberId) {
        if (orderRepository.existsByMemberId(memberId)) {
            return MemberType.OLD_MEMBER;
        }
        return MemberType.NEW_MEMBER;
    }
}
