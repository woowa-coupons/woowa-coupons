package woowa.promotion.app.member_coupon.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import woowa.promotion.app.member_coupon.domain.MemberCoupon;

public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long> {

    boolean existsByMemberIdAndCouponIdIn(Long memberId, List<Long> couponIds);
}
