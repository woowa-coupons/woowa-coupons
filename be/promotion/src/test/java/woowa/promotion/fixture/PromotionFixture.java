package woowa.promotion.fixture;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import woowa.promotion.admin.promotion.domain.ProgressStatus;

public enum PromotionFixture {

    A_프로모션("누구나 최대 1만원", "먹고 싶은 음식을 고르세요", "www.bannerUrl.com", Instant.now(), Instant.now(),
            "www.promotionUrl.com", true, ProgressStatus.ON_GOING.name()),
    B_프로모션("나만 10만원", "먹기 싫은"
            + " 음식을 고르세요", "www.bannerUrl.com", Instant.now(), Instant.now(),
            "www.promotionUrl.com", true, ProgressStatus.ON_GOING.name()),
    C_프로모션("추석 이벤트", "즐거운 한가위"
            + " 음식을 고르세요", "www.bannerUrl.com", Instant.now(), Instant.now().plus(1, ChronoUnit.DAYS),
            "www.promotionUrl.com", false, ProgressStatus.ON_GOING.name()),
    추석_프로모션("추석 프로모션", "즐거운 한가위", "www.naver.com", Instant.now(), Instant.now().plus(1, ChronoUnit.DAYS),
            "www.google.com", true, ProgressStatus.ON_GOING.name());
    private final String title;
    private final String content;
    private final String banner;
    private final Instant startedAt;
    private final Instant finishedAt;
    private final String promotionPageUrl;
    private final boolean isDisplay;
    private final String progressStatus;

    PromotionFixture(String title, String content, String banner, Instant startedAt, Instant finishedAt,
                     String promotionPageUrl, boolean isDisplay, String progressStatus) {
        this.title = title;
        this.content = content;
        this.banner = banner;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.promotionPageUrl = promotionPageUrl;
        this.isDisplay = isDisplay;
        this.progressStatus = progressStatus;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getBanner() {
        return banner;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public Instant getFinishedAt() {
        return finishedAt;
    }

    public String getPromotionPageUrl() {
        return promotionPageUrl;
    }

    public boolean isDisplay() {
        return isDisplay;
    }

    public String getProgressStatus() {
        return progressStatus;
    }
}
