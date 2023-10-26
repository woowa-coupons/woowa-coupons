package woowa.promotion.app.member_coupon.application;

import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowa.promotion.admin.coupon.infrastructure.CouponRepository;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.admin.promotion_option.domain.PromotionOption;
import woowa.promotion.admin.promotion_option.infrastructure.PromotionOptionRepository;
import woowa.promotion.admin.promotion_option_coupon_group.domain.PromotionOptionCouponGroup;
import woowa.promotion.admin.promotion_option_coupon_group.infrastructure.PromotionOptionCouponGroupRepository;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.app.member_coupon.application.condition.PromotionOptionCondition;
import woowa.promotion.app.member_coupon.infrastructure.MemberCouponRepository;
import woowa.promotion.app.member_coupon.presentation.dto.CouponIssueRequest;
import woowa.promotion.app.order.domain.Order;
import woowa.promotion.app.order.infrastructure.OrderRepository;
import woowa.promotion.global.exception.ApiException;
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
                        .filter(couponGroup -> !isExpiredCouponGroup(couponGroup))
                        .filter(couponGroup -> !isAlreadyIssued(member, couponGroup))
                        .findFirst())
                .orElseThrow(() -> new ApiException(CouponGroupException.NOT_FOUND));
    }

    private boolean isMemberSatisfied(Member member, List<PromotionOptionCondition> conditions) {
        Order lastOrder = orderRepository.findLastOneByMemberId(member.getId());

        return conditions.stream()
                .allMatch(condition -> condition.isSatisfied(lastOrder));
    }

    private List<CouponGroup> getCouponGroups(PromotionOption promotionOption) {
        List<PromotionOptionCouponGroup> promotionOptionCouponGroups = promotionOptionCouponGroupRepository.findByPromotionOptionId(promotionOption.getId());
        return promotionOptionCouponGroups.stream()
                .map(PromotionOptionCouponGroup::getCouponGroup)
                .toList();
    }

    private boolean isExpiredCouponGroup(CouponGroup couponGroup) {
        return Instant.now().isAfter(couponGroup.getFinishedAt());
    }

    private boolean isAlreadyIssued(Member member, CouponGroup couponGroup) {
        List<Long> couponIds = couponRepository.findIdsByCouponGroupId(couponGroup.getId());
        return memberCouponRepository.existsByMemberIdAndCouponIdIn(member.getId(), couponIds);
    }

}
