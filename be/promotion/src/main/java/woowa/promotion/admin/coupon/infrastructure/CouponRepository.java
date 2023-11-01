package woowa.promotion.admin.coupon.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import woowa.promotion.admin.coupon.domain.Coupon;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("SELECT coupon.id FROM Coupon coupon WHERE coupon.couponGroup.id = :couponGroupId")
    List<Long> findIdsByCouponGroupId(@Param("couponGroupId") Long couponGroupId);

    List<Coupon> findByCouponGroupId(@Param("couponGroupId") Long couponGroupId);
}
