package woowa.promotion.application.app;

import static woowa.promotion.util.fixture.CouponFixture.추석_쿠폰_신규;
import static woowa.promotion.util.fixture.CouponGroupFixture.추석_쿠폰그룹_기존;
import static woowa.promotion.util.fixture.FixtureFactory.createCoupon;
import static woowa.promotion.util.fixture.FixtureFactory.createCouponGroup;
import static woowa.promotion.util.fixture.FixtureFactory.createPromotion;
import static woowa.promotion.util.fixture.FixtureFactory.createPromotionOption;
import static woowa.promotion.util.fixture.PromotionFixture.추석_프로모션;
import static woowa.promotion.util.fixture.PromotionOptionFixture.네번째_프로모션_옵션;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import woowa.promotion.admin.promotion.domain.Promotion;
import woowa.promotion.admin.promotion_option.domain.PromotionOption;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.app.member_coupon.application.MemberCouponService;
import woowa.promotion.app.member_coupon.domain.MemberCoupon;
import woowa.promotion.app.member_coupon.presentation.dto.CouponIssueRequest;
import woowa.promotion.util.AcceptanceTest;
import woowa.promotion.util.fixture.CouponFixture;

@DisplayName("[비즈니스 로직 테스트][회원] 쿠폰 발급 - 동시성")
public class MemberCouponTest extends AcceptanceTest { // ApplicationTest에 트랜잭션 어노테이션이 있어 AcceptanceTest로 실행

    @Autowired
    private MemberCouponService memberCouponService;

    @Test
    void issueCoupon() throws InterruptedException {
        // given
        Long promotionId = savePromotion();

        int couponAmount = CouponFixture.추석_쿠폰_신규.getInitialQuantity();
        int memberCount = couponAmount + 100;

        ExecutorService executorService = Executors.newFixedThreadPool(30);
        CountDownLatch latch = new CountDownLatch(memberCount);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        List<Member> members = new ArrayList<>();
        for (int i = 0; i < memberCount; i++) {
            Member member = 랜덤_회원_가입();
            members.add(member);
        }

        // when
        for (int i = 0; i < memberCount; i++) {
            Member member = members.get(i);
            executorService.submit(() -> {
                try {
                    memberCouponService.issueCoupon(new CouponIssueRequest(promotionId), member);
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    failCount.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        // then
        List<MemberCoupon> memberCoupons = supportRepository.findAll(MemberCoupon.class);
        Assertions.assertThat(memberCoupons.size()).isEqualTo(couponAmount);
    }

    private Long savePromotion() {
        var couponGroup = makeCouponGroup(createCouponGroup(추석_쿠폰그룹_기존));
        makeCoupon(createCoupon(추석_쿠폰_신규, couponGroup));
        Promotion promotion = makePromotion(createPromotion(추석_프로모션));
        PromotionOption promotionOption = makePromotionOption(createPromotionOption(네번째_프로모션_옵션, promotion));
        makePromotionOptionCouponGroup(promotionOption, couponGroup);
        return promotion.getId();
    }
}
