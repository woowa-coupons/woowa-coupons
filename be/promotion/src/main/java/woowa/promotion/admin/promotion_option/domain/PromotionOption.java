package woowa.promotion.admin.promotion_option.domain;

import java.time.Instant;
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

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PromotionOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant lastOrderAt;

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
