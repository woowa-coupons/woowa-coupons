package woowa.promotion.admin.coupon_group.domain;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import woowa.promotion.admin.promotion.domain.Promotion;

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

    @JoinColumn(name = "promotion_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Promotion promotion;

    @Builder
    private CouponGroup(String title, Instant startedAt, Instant finishedAt, String adminNickname) {
        this.title = title;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.adminNickname = adminNickname;
    }
}
