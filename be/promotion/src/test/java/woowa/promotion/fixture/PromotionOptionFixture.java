package woowa.promotion.fixture;

import java.time.Instant;
import java.time.Year;
import java.time.ZoneId;
import woowa.promotion.admin.promotion_option.domain.MemberType;

public enum PromotionOptionFixture {

    A_프로모션_옵션(Instant.now(), true, MemberType.NEW_MEMBER.name()),
    B_프로모션_옵션(Instant.now(), false, MemberType.OLD_MEMBER.name()),
    첫번째_프로모션_옵션(getLastOrderAtPromotion(), true, MemberType.OLD_MEMBER.name()),
    두번째_프로모션_옵션(getLastOrderAtPromotion(), false, MemberType.OLD_MEMBER.name()),
    세번째_프로모션_옵션(null, null, MemberType.NEW_MEMBER.name()),
    네번째_프로모션_옵션(null, null, MemberType.OLD_MEMBER.name());


    private static Instant getLastOrderAtPromotion() {
        return Year.of(2023).atMonth(10).atDay(26).atTime(16, 0).atZone(ZoneId.of("Asia/Seoul")).toInstant();
    }

    private Instant lastOrderAt;
    private Boolean lastOrderBefore;
    private String memberType;

    PromotionOptionFixture(Instant lastOrderAt, Boolean lastOrderBefore, String memberType) {
        this.lastOrderAt = lastOrderAt;
        this.lastOrderBefore = lastOrderBefore;
        this.memberType = memberType;
    }

    public Instant getLastOrderAt() {
        return lastOrderAt;
    }

    public Boolean getLastOrderBefore() {
        return lastOrderBefore;
    }

    public String getMemberType() {
        return memberType;
    }
}
