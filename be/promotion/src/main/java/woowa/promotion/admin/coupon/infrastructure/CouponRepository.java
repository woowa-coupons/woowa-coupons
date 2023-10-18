package woowa.promotion.admin.coupon.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import woowa.promotion.admin.coupon.domain.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
