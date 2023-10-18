package woowa.promotion.admin.coupon_group.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;

public interface CouponGroupRepository extends JpaRepository<CouponGroup, Long> {
}
