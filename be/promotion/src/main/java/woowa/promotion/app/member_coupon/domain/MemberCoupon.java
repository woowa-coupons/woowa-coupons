package woowa.promotion.app.member_coupon.domain;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
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
    @CreatedDate
    private Instant issuedAt;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "coupon_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Coupon coupon;

    public MemberCoupon(Member member, Coupon coupon) {
        this.member = member;
        this.coupon = coupon;
    }
}
