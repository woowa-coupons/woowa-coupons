package woowa.promotion.fixture;

import woowa.promotion.admin.coupon_group.domain.Type;

import java.time.Instant;

public enum CouponGroupFixture {

    A_쿠폰그룹("A_쿠폰그룹", Instant.now(), Instant.now(), Type.EVERYDAY),
    B_쿠폰그룹("B_쿠폰그룹", Instant.now(), Instant.now(), Type.PERIOD);

    private final String title;
    private final Instant startedAt;
    private final Instant finishedAt;
    private final Type type;

    CouponGroupFixture(String title, Instant startedAt, Instant finishedAt, Type type) {
        this.title = title;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.type = type;
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
}
