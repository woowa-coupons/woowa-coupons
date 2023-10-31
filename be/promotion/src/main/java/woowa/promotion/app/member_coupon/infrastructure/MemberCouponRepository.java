package woowa.promotion.app.member_coupon.infrastructure;

import java.time.Instant;
import java.util.List;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import woowa.promotion.app.member_coupon.domain.MemberCoupon;

public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long> {

    boolean existsByMemberIdAndCouponIdIn(Long memberId, List<Long> couponIds);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT COUNT(memberCoupon .id) > 0  " +
            "FROM MemberCoupon memberCoupon " +
            "WHERE memberCoupon.member.id= :memberId " +
            "AND memberCoupon.coupon.id IN :couponIds " +
            "AND memberCoupon.issuedAt BETWEEN :start AND :end")
    boolean existsByMemberIdAndCouponIdInAndToday(@Param("memberId") Long memberId,
                                                  @Param("couponIds") List<Long> couponIds,
                                                  @Param("start") Instant start, @Param("end") Instant end);

}
