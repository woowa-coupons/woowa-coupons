package woowa.promotion.admin.promotion_option_coupon_group.infrastructure;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import woowa.promotion.admin.promotion_option_coupon_group.domain.PromotionOptionCouponGroup;

public interface PromotionOptionCouponGroupRepository extends JpaRepository<PromotionOptionCouponGroup, Long> {
    Optional<PromotionOptionCouponGroup> findByPromotionOptionId(Long id);
}
