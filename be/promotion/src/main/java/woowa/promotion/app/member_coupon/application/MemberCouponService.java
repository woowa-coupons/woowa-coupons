package woowa.promotion.app.member_coupon.application;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowa.promotion.admin.promotion_option.domain.MemberType;
import woowa.promotion.app.order.infrastructure.OrderRepository;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberCouponService {

    private final OrderRepository orderRepository;

    // OrderService가 없어서 임의로 로직 생성
    private MemberType findMemberType(Long memberId) {
        if (orderRepository.existsByMemberId(memberId)) {
            return MemberType.OLD_MEMBER;
        }
        return MemberType.NEW_MEMBER;
    }
}
