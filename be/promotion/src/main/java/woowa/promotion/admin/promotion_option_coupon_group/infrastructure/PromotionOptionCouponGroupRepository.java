package woowa.promotion.admin.promotion_option_coupon_group.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import woowa.promotion.admin.promotion_option_coupon_group.domain.PromotionOptionCouponGroup;

public interface PromotionOptionCouponGroupRepository extends JpaRepository<PromotionOptionCouponGroup, Long> {

    @Query("SELECT promotionOptionCouponGroup " +
            "FROM PromotionOptionCouponGroup promotionOptionCouponGroup " +
            "JOIN FETCH promotionOptionCouponGroup.couponGroup couponGroup " +
            "WHERE promotionOptionCouponGroup.promotionOption.id = :id")
    List<PromotionOptionCouponGroup> findByPromotionOptionId(@Param("id") Long id);
}
