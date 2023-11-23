package woowa.promotion.admin.promotion_option_coupon_group.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.admin.promotion_option.domain.PromotionOption;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(name = "po_cg_idx", columnList = "promotion_option_id, coupon_group_id")
})
@Entity
public class PromotionOptionCouponGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "promotion_option_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private PromotionOption promotionOption;

    @JoinColumn(name = "coupon_group_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private CouponGroup couponGroup;

    public PromotionOptionCouponGroup(PromotionOption promotionOption, CouponGroup couponGroup) {
        this.promotionOption = promotionOption;
        this.couponGroup = couponGroup;
    }

    public boolean matchesPromotionId(Long promotionId) {
        return couponGroup.getPromotion().getId().equals(promotionId);
    }
}
