package woowa.promotion.app.member_coupon.application;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowa.promotion.admin.coupon.domain.Coupon;
import woowa.promotion.admin.coupon.infrastructure.CouponRepository;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.admin.coupon_group.domain.Type;
import woowa.promotion.admin.promotion_option.domain.MemberType;
import woowa.promotion.admin.promotion_option.domain.PromotionOption;
import woowa.promotion.admin.promotion_option.infrastructure.PromotionOptionRepository;
import woowa.promotion.admin.promotion_option_coupon_group.domain.PromotionOptionCouponGroup;
import woowa.promotion.admin.promotion_option_coupon_group.infrastructure.PromotionOptionCouponGroupRepository;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.app.member_coupon.domain.MemberCoupon;
import woowa.promotion.app.member_coupon.infrastructure.MemberCouponRepository;
import woowa.promotion.app.order.infrastructure.OrderRepository;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.exception.domain.CouponException;
import woowa.promotion.global.exception.domain.PromotionException;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberCouponService {

    private final MemberCouponRepository memberCouponRepository;
    private final OrderRepository orderRepository;
    private final CouponRepository couponRepository;
    private final PromotionOptionRepository promotionOptionRepository;
    private final PromotionOptionCouponGroupRepository promotionOptionCouponGroupRepository;

    @Transactional
    public void issueCoupon(Member member, Long promotionId) {
        // 1. 프로모션 조건 확인
        List<PromotionOption> options = promotionOptionRepository.findByPromotionId(promotionId);

        // 프로모션 조건 중 회원이 해당하는 조건을 찾기 위해 순회
        for (PromotionOption option : options) {  // 마지막 주문일 & 랜덤, 마지막 주문일, 랜덤

            if (isEligibleForPromotion(member.getId(), option)) { // 멤버 타입, 마지막 주문일 조건 체크
                List<PromotionOptionCouponGroup> promotionOptionCouponGroups =
                        promotionOptionCouponGroupRepository.findByPromotionOptionId(option.getId());

                for (PromotionOptionCouponGroup optionCouponGroup : promotionOptionCouponGroups) {
                    CouponGroup couponGroup = optionCouponGroup.getCouponGroup();

                    if (isParticipate(member.getId(), couponGroup)) {
                        throw new ApiException(PromotionException.ALREADY_PARTICIPATE);
                    }

                    if (option.getIsRandom()) {
                        issueRandomCoupon(couponGroup.getId(), member);
                        return;
                    }
                    saveMemberCoupon(member, couponGroup.getOneCoupon());
                }
            }
        }
    }

    private boolean isParticipate(Long memberId, CouponGroup couponGroup) {
        if (couponGroup.getType() == Type.EVERYDAY) {
            return memberCouponRepository.existsByMemberIdAndIssuedAt(memberId, Instant.now()); // 오늘 안에 발급받았는지 체크하는 로직 이거 맞아?
        }
        if (couponGroup.getType() == Type.PERIOD) {
            return memberCouponRepository.existsByMemberIdAndIssuedAtBetween(
                    memberId,
                    couponGroup.getStartedAt(),
                    couponGroup.getFinishedAt()
            );
        }
        return false;
    }

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
        MemberType memberType = findMemberType(memberId);
        if (promotionOption.getMemberType() != memberType) {
            return false;
        }

        Instant lastOrderAt = promotionOption.getLastOrderAt();

        if (lastOrderAt != null) {

            if (promotionOption.getLastOrderBefore() == true) {
                if (orderRepository.existsByCreatedAtAfter(lastOrderAt)) {
                    return false;
                }
                return true;
            }
            return false;
        }
        return true;
    }

    // OrderService가 없어서 임의로 로직 생성
    private MemberType findMemberType(Long memberId) {
        if (orderRepository.existsByMemberId(memberId)) {
            return MemberType.OLD_MEMBER;
        }
        return MemberType.NEW_MEMBER;
    }
}
