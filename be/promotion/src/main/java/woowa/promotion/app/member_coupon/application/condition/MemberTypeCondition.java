package woowa.promotion.app.member_coupon.application.condition;

import woowa.promotion.admin.promotion_option.domain.MemberType;
import woowa.promotion.app.order.domain.Order;

public class MemberTypeCondition implements PromotionOptionCondition {

    private final MemberType memberType;

    public MemberTypeCondition(MemberType memberType) {
        this.memberType = memberType;
    }

    @Override
    public boolean isSatisfied(Order lastOrder) {
        if (lastOrder == null) {
            return memberType == MemberType.NEW_MEMBER;
        }
        return memberType == MemberType.OLD_MEMBER;
    }
}
