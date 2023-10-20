package woowa.promotion.fixture;

import java.time.Instant;

public enum PromotionOptionFixture {

    A_프로모션_옵션(Instant.now(), true, "NEW_MEMBER"),
    B_프로모션_옵션(Instant.now(), false, "OLD_MEMBER");
    
    private Instant lastOrderAt;

    private Boolean isRandom;
    private String memberType;


    PromotionOptionFixture(Instant lastOrderAt, Boolean isRandom, String memberType) {
        this.lastOrderAt = lastOrderAt;
        this.isRandom = isRandom;
        this.memberType = memberType;
    }

    public Instant getLastOrderAt() {
        return lastOrderAt;
    }

    public Boolean getRandom() {
        return isRandom;
    }

    public String getMemberType() {
        return memberType;
    }
}
