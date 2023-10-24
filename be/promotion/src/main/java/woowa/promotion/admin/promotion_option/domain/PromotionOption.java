package woowa.promotion.admin.promotion_option.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import woowa.promotion.admin.promotion.domain.Promotion;

import javax.persistence.*;
import java.time.Instant;

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

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean isRandom;

    @JoinColumn(name = "promotion_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Promotion promotion;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    public PromotionOption(Instant lastOrderAt, Boolean isRandom, Promotion promotion, MemberType memberType) {
        this.lastOrderAt = lastOrderAt;
        this.isRandom = isRandom;
        this.promotion = promotion;
        this.memberType = memberType;
    }
}
