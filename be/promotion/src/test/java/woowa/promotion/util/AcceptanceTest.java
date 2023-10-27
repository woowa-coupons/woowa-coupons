package woowa.promotion.util;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import woowa.promotion.acceptance.SupportRepository;
import woowa.promotion.admin.coupon.domain.Coupon;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.admin.promotion.domain.Promotion;
import woowa.promotion.admin.promotion_option.domain.PromotionOption;
import woowa.promotion.admin.promotion_option_coupon_group.domain.PromotionOptionCouponGroup;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.fixture.FixtureFactory;
import woowa.promotion.fixture.UserFixture;
import woowa.promotion.global.domain.jwt.JwtProvider;
import woowa.promotion.global.security.hash.PasswordEncoder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(value = {"classpath:schema.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public abstract class AcceptanceTest {

    @LocalServerPort
    private int port;

    @Autowired
    protected SupportRepository supportRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected JwtProvider jwtProvider;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    protected Member makeMember(UserFixture userFixture) {
        String password = passwordEncoder.encrypt(userFixture.getPassword());
        return supportRepository.save(FixtureFactory.createMember(userFixture, password));
    }

    protected Promotion makePromotion(Promotion promotion) {
        return supportRepository.save(promotion);
    }

    protected CouponGroup makeCouponGroup(CouponGroup couponGroup) {
        return supportRepository.save(couponGroup);
    }

    protected Coupon makeCoupon(Coupon coupon) {
        return supportRepository.save(coupon);
    }

    protected PromotionOptionCouponGroup makePromotionOptionCouponGroup(PromotionOption promotionOption,
                                                                        CouponGroup couponGroup) {
        PromotionOptionCouponGroup promotionOptionCouponGroup = new PromotionOptionCouponGroup(promotionOption,
                couponGroup);
        return supportRepository.save(promotionOptionCouponGroup);
    }

    protected PromotionOption makePromotionOption(PromotionOption promotionOption) {
        return supportRepository.save(promotionOption);
    }
}

