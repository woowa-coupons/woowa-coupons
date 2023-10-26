package woowa.promotion.app.member_coupon.domain;

import java.time.Instant;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import woowa.promotion.admin.coupon.domain.Coupon;
import woowa.promotion.app.member.domain.Member;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MemberCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Instant issuedAt;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "coupon_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Coupon coupon;

    private MemberCoupon(Instant issuedAt, Member member, Coupon coupon) {
        this.issuedAt = issuedAt;
        this.member = member;
        this.coupon = coupon;
    }

    public static MemberCoupon of(Member member, Coupon coupon) {
        return new MemberCoupon(Instant.now(), member, coupon);
    }

}
