package woowa.promotion.app.member_coupon.domain;

import java.time.Instant;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import woowa.promotion.admin.coupon.domain.Coupon;
import woowa.promotion.app.member.domain.Member;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class MemberCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false)
    private Instant issuedAt;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "coupon_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Coupon coupon;

    private MemberCoupon(Member member, Coupon coupon) {
        this.member = member;
        this.coupon = coupon;
    }

    public static MemberCoupon of(Member member, Coupon coupon) {
        return new MemberCoupon(member, coupon);
    }

}
