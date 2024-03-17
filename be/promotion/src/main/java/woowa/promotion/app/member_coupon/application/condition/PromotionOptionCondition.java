package woowa.promotion.app.member_coupon.application.condition;

import woowa.promotion.app.order.domain.Order;

public interface PromotionOptionCondition {

    boolean isSatisfied(Order lastOrder);

}
