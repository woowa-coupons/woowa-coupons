package woowa.promotion.app.member_coupon.infrastructure;

import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import woowa.promotion.app.member_coupon.domain.MemberCoupon;

public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long> {

    boolean existsByMemberIdAndCouponIdIn(Long memberId, List<Long> couponIds);

    @Query(value = "SELECT EXISTS (SELECT 1 " +
            "FROM member_coupon " +
            "WHERE member_id = :memberId " +
            "AND coupon_id IN :couponIds " +
            "AND issued_at BETWEEN :start AND :end)",
            nativeQuery = true)
    boolean existsByMemberIdAndCouponIdInAndToday(@Param("memberId") Long memberId,
                                                  @Param("couponIds") List<Long> couponIds,
                                                  @Param("start") Instant start, @Param("end") Instant end);

}
