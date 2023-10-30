package woowa.promotion.admin.coupon.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import woowa.promotion.admin.coupon.domain.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("SELECT coupon.id FROM Coupon coupon WHERE coupon.couponGroup.id = :couponGroupId")
    List<Long> findIdsByCouponGroupId(@Param("couponGroupId") Long couponGroupId);
}
