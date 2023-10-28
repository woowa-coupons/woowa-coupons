package woowa.promotion.admin.promotion_option.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import woowa.promotion.admin.promotion.domain.Promotion;
import woowa.promotion.app.member_coupon.application.condition.LastOrderCondition;
import woowa.promotion.app.member_coupon.application.condition.MemberTypeCondition;
import woowa.promotion.app.member_coupon.application.condition.PromotionOptionCondition;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PromotionOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant lastOrderAt;

    @Column(columnDefinition = "TINYINT")
    private Boolean lastOrderBefore;

    @JoinColumn(name = "promotion_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Promotion promotion;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    public PromotionOption(Instant lastOrderAt, Boolean lastOrderBefore, Promotion promotion, MemberType memberType) {
        this.lastOrderAt = lastOrderAt;
        this.lastOrderBefore = lastOrderBefore;
        this.promotion = promotion;
        this.memberType = memberType;
    }

    public List<PromotionOptionCondition> getConditions() {
        List<PromotionOptionCondition> conditions = new ArrayList<>();

        if (lastOrderAt != null && lastOrderBefore != null) {
            conditions.add(new LastOrderCondition(lastOrderAt, lastOrderBefore));
        }

        conditions.add(new MemberTypeCondition(memberType));
        return conditions;
    }

}
