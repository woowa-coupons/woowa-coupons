package woowa.promotion.fixture;

import java.time.Instant;

public enum PromotionOptionFixture {

    A_프로모션_옵션(Instant.now(), "NEW_MEMBER"),
    B_프로모션_옵션(Instant.now(), "OLD_MEMBER");

    private Instant lastOrderAt;
    private String memberType;

    PromotionOptionFixture(Instant lastOrderAt, String memberType) {
        this.lastOrderAt = lastOrderAt;
        this.memberType = memberType;
    }

    public Instant getLastOrderAt() {
        return lastOrderAt;
    }

    public String getMemberType() {
        return memberType;
    }
}
