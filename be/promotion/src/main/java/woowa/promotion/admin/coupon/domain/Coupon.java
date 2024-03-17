package woowa.promotion.admin.coupon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.global.domain.audit.AuditingFields;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.exception.domain.CouponException;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(name = "coupon_group_id_idx", columnList = "coupon_group_id")
})
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

    @Version
    private Integer version;

    @Column(nullable = false)
    private Integer remainQuantity;

    @JoinColumn(name = "coupon_group_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private CouponGroup couponGroup;

    @Builder
    private Coupon(String title, Integer discount, CouponType type, Integer initialQuantity, CouponGroup couponGroup) {
        this.title = title;
        this.discount = discount;
        this.type = type;
        this.initialQuantity = initialQuantity;
        this.remainQuantity = initialQuantity;
        this.couponGroup = couponGroup;
    }

    public void issue() {
        if (this.remainQuantity <= 0) {
            throw new ApiException(CouponException.EXHAUSTED);
        }
        this.remainQuantity--;
    }

}
