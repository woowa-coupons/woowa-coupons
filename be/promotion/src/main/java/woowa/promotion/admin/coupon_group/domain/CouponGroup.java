package woowa.promotion.admin.coupon_group.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import woowa.promotion.admin.coupon.domain.Coupon;
import woowa.promotion.admin.promotion.domain.Promotion;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CouponGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private Instant startedAt;

    @Column(nullable = false)
    private Instant finishedAt;

    @Column(nullable = false)
    private String adminNickname;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean isRandom;

    @JoinColumn(name = "promotion_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Promotion promotion;

    @OneToMany(mappedBy = "couponGroup")
    private Set<Coupon> coupons;

    @Builder
    private CouponGroup(String title, Instant startedAt, Instant finishedAt, String adminNickname, Type type, Boolean isRandom) {
        this.title = title;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.adminNickname = adminNickname;
        this.type = type;
        this.isRandom = isRandom;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

}
