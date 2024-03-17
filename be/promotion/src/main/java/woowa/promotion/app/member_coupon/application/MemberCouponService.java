package woowa.promotion.app.member_coupon.application;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowa.promotion.admin.coupon.domain.Coupon;
import woowa.promotion.admin.coupon.infrastructure.CouponRepository;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.admin.coupon_group.domain.Type;
import woowa.promotion.admin.promotion_option.domain.PromotionOption;
import woowa.promotion.admin.promotion_option.infrastructure.PromotionOptionRepository;
import woowa.promotion.admin.promotion_option_coupon_group.domain.PromotionOptionCouponGroup;
import woowa.promotion.admin.promotion_option_coupon_group.infrastructure.PromotionOptionCouponGroupRepository;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.app.member_coupon.application.condition.PromotionOptionCondition;
import woowa.promotion.app.member_coupon.domain.MemberCoupon;
import woowa.promotion.app.member_coupon.infrastructure.MemberCouponRepository;
import woowa.promotion.app.member_coupon.presentation.dto.CouponIssueRequest;
import woowa.promotion.app.order.domain.Order;
import woowa.promotion.app.order.infrastructure.OrderRepository;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.exception.domain.CouponException;
import woowa.promotion.global.exception.domain.CouponGroupException;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberCouponService {

    private final OrderRepository orderRepository;
    private final PromotionOptionRepository promotionOptionRepository;
    private final PromotionOptionCouponGroupRepository promotionOptionCouponGroupRepository;
    private final MemberCouponRepository memberCouponRepository;
    private final CouponRepository couponRepository;

    @Transactional
    public void issueCoupon(CouponIssueRequest request, Member member) {
        List<PromotionOption> promotionOptions = promotionOptionRepository.findByPromotionId(request.promotionId());

        CouponGroup allMatchedCouponGroup = promotionOptions.stream()
                .filter(promotionOption -> isMemberSatisfied(member, promotionOption.getConditions()))
                .map(this::getCouponGroups)
                .findFirst()
                .flatMap(couponGroups -> couponGroups.stream()
                        .filter(this::hasRemainCoupon)
                        .filter(couponGroup -> !isExpiredCouponGroup(couponGroup))
                        .filter(couponGroup -> !isAlreadyIssued(member, couponGroup))
                        .findFirst())
                .orElseThrow(() -> new ApiException(CouponGroupException.NOT_FOUND));

        issueCouponInCouponGroup(allMatchedCouponGroup, member);
    }

    private boolean isMemberSatisfied(Member member, List<PromotionOptionCondition> conditions) {
        Order lastOrder = orderRepository.findLastOneByMemberId(member.getId());

        return conditions.stream()
                .allMatch(condition -> condition.isSatisfied(lastOrder));
    }

    private List<CouponGroup> getCouponGroups(PromotionOption promotionOption) {
        List<PromotionOptionCouponGroup> promotionOptionCouponGroups = promotionOptionCouponGroupRepository.findByPromotionOptionId(
                promotionOption.getId());
        return promotionOptionCouponGroups.stream()
                .map(PromotionOptionCouponGroup::getCouponGroup)
                .toList();
    }

    private boolean hasRemainCoupon(CouponGroup couponGroup) {
        return couponRepository.findByCouponGroupId(couponGroup.getId()).stream()
                .allMatch(coupon -> coupon.getRemainQuantity() > 0);
    }

    private boolean isExpiredCouponGroup(CouponGroup couponGroup) {
        return Instant.now().isAfter(couponGroup.getFinishedAt());
    }

    private boolean isAlreadyIssued(Member member, CouponGroup couponGroup) {
        List<Long> couponIds = couponRepository.findIdsByCouponGroupId(couponGroup.getId());
        if (couponGroup.getType() == Type.PERIOD) {
            return memberCouponRepository.existsByMemberIdAndCouponIdIn(member.getId(), couponIds);
        }
        return memberCouponRepository.existsByMemberIdAndCouponIdInAndToday(member.getId(),
                couponIds,
                Instant.now().truncatedTo(ChronoUnit.DAYS),
                Instant.now().truncatedTo(ChronoUnit.DAYS).plus(1, ChronoUnit.DAYS));
    }

    private void issueCouponInCouponGroup(CouponGroup couponGroup, Member member) {
        Set<Coupon> coupons = couponGroup.getCoupons();

        Coupon coupon = selectCoupon(coupons, couponGroup.getIsRandom());

        issue(member, coupon);
    }

    private Coupon selectCoupon(Set<Coupon> coupons, boolean isRandom) {
        if (isRandom) {
            return coupons.stream()
                    .findAny()
                    .orElseThrow(() -> new ApiException(CouponException.NOT_FOUND));
        }
        return coupons.stream()
                .findFirst()
                .orElseThrow(() -> new ApiException(CouponException.NOT_FOUND));
    }

    private void issue(Member member, Coupon coupon) {
        coupon.issue();
        memberCouponRepository.save(MemberCoupon.of(member, coupon));
    }

}
