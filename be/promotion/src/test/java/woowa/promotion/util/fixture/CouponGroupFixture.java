package woowa.promotion.util.fixture;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import woowa.promotion.admin.coupon_group.domain.Type;

public enum CouponGroupFixture {

    A_쿠폰그룹("A_쿠폰그룹", Instant.now(), Instant.now(), Type.EVERYDAY, true),
    B_쿠폰그룹("B_쿠폰그룹", Instant.now(), Instant.now(), Type.PERIOD, false),
    추석_쿠폰그룹_기존("추석 쿠폰 그룹 기존", Instant.now(), Instant.now().plus(1, ChronoUnit.DAYS), Type.PERIOD, false),
    추석_쿠폰그룹_랜덤("추석 쿠폰 그룹 랜덤", Instant.now(), Instant.now().plus(1, ChronoUnit.DAYS), Type.PERIOD, true);


    private final String title;
    private final Instant startedAt;
    private final Instant finishedAt;
    private final Type type;
    private final Boolean isRandom;

    CouponGroupFixture(String title, Instant startedAt, Instant finishedAt, Type type, Boolean isRandom) {
        this.title = title;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.type = type;
        this.isRandom = isRandom;
    }

    public String getTitle() {
        return title;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public Instant getFinishedAt() {
        return finishedAt;
    }

    public Type getType() {
        return type;
    }

    public Boolean getIsRandom() {
        return isRandom;
    }

}
