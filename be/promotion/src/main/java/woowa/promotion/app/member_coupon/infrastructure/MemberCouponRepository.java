package woowa.promotion.app.member_coupon.infrastructure;

import java.time.Instant;
import org.springframework.data.jpa.repository.JpaRepository;
import woowa.promotion.app.member_coupon.domain.MemberCoupon;

public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long> {

    boolean existsByMemberIdAndIssuedAt(Long memberId, Instant today); // 오류 있음

    boolean existsByMemberIdAndIssuedAtBetween(Long memberId, Instant startedAt, Instant finishedAt);
}
