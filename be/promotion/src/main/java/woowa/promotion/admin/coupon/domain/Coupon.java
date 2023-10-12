package woowa.promotion.admin.coupon.domain;

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
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.global.domain.audit.AuditingFields;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Coupon extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private Integer discount;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private CouponType type;

    @Column(nullable = false)
    private Integer initialQuantity;

    @Column(nullable = false)
    private Integer remainQuantity;

    @JoinColumn(name = "coupon_group_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private CouponGroup couponGroup;
    
}
