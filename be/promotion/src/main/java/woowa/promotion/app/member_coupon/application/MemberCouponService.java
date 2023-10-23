package woowa.promotion.app.member_coupon.application;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowa.promotion.admin.promotion_option.domain.MemberType;
import woowa.promotion.admin.promotion_option.domain.PromotionOption;
import woowa.promotion.app.order.infrastructure.OrderRepository;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberCouponService {

    private final OrderRepository orderRepository;

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
