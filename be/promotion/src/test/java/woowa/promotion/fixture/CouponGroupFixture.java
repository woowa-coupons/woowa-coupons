package woowa.promotion.fixture;

import java.time.Instant;

public enum CouponGroupFixture {

    A_쿠폰그룹("A_쿠폰그룹", Instant.now(), Instant.now()),
    B_쿠폰그룹("B_쿠폰그룹", Instant.now(), Instant.now());

    private final String title;
    private final Instant startedAt;
    private final Instant finishedAt;

    CouponGroupFixture(String title, Instant startedAt, Instant finishedAt) {
        this.title = title;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
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
}
