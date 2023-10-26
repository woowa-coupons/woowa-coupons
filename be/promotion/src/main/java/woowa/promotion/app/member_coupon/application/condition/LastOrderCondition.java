package woowa.promotion.app.member_coupon.application.condition;

import java.time.Instant;
import woowa.promotion.app.order.domain.Order;

public class LastOrderCondition implements PromotionOptionCondition {

    private final Instant lastOrderedAt;
    private final Boolean lastOrderBefore;

    public LastOrderCondition(Instant lastOrderedAt, Boolean lastOrderBefore) {
        this.lastOrderedAt = lastOrderedAt;
        this.lastOrderBefore = lastOrderBefore;
    }

    @Override
    public boolean isSatisfied(Order lastOrder) {
        if (lastOrder == null) {
            return true;
        }

        Instant lastOrderedAt = lastOrder.getCreatedAt();
        if (lastOrderBefore) {
            return lastOrderedAt.isBefore(this.lastOrderedAt);
        }
        return lastOrderedAt.isAfter(this.lastOrderedAt);
    }
}
