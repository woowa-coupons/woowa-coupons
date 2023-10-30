package woowa.promotion.application.admin.promotion.application;

import static org.assertj.core.api.Assertions.assertThat;
import static woowa.promotion.fixture.CouponGroupFixture.A_쿠폰그룹;
import static woowa.promotion.fixture.CouponGroupFixture.B_쿠폰그룹;
import static woowa.promotion.fixture.FixtureFactory.createCouponGroup;
import static woowa.promotion.fixture.FixtureFactory.createPromotion;
import static woowa.promotion.fixture.FixtureFactory.createPromotionOption;
import static woowa.promotion.fixture.FixtureFactory.createPromotionOptionRequest;
import static woowa.promotion.fixture.FixtureFactory.createPromotionRegisterRequest;
import static woowa.promotion.fixture.PromotionFixture.A_프로모션;
import static woowa.promotion.fixture.PromotionFixture.B_프로모션;
import static woowa.promotion.fixture.PromotionOptionFixture.A_프로모션_옵션;
import static woowa.promotion.fixture.PromotionOptionFixture.B_프로모션_옵션;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.admin.coupon_group.infrastructure.CouponGroupRepository;
import woowa.promotion.admin.promotion.application.PromotionService;
import woowa.promotion.admin.promotion.application.dto.response.PromotionListResponse;
import woowa.promotion.admin.promotion.domain.Promotion;
import woowa.promotion.admin.promotion.infrastructure.PromotionRepository;
import woowa.promotion.admin.promotion_option.domain.PromotionOption;
import woowa.promotion.admin.promotion_option.infrastructure.PromotionOptionRepository;
import woowa.promotion.admin.promotion_option_coupon_group.domain.PromotionOptionCouponGroup;
import woowa.promotion.admin.promotion_option_coupon_group.infrastructure.PromotionOptionCouponGroupRepository;
import woowa.promotion.util.ApplicationTest;


@DisplayName("[비즈니스 로직 테스트] 관리자 프로모션")
class PromotionServiceTest extends ApplicationTest {

    @Autowired
    private PromotionService promotionService;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private CouponGroupRepository couponGroupRepository;
    @Autowired
    private PromotionOptionRepository promotionOptionRepository;
    @Autowired
    private PromotionOptionCouponGroupRepository promotionOptionCouponGroupRepository;


    @DisplayName("프로모션 등록")
    @Test
    void register() {
        // given
        var couponGroupA = makeCouponGroup(createCouponGroup(A_쿠폰그룹));
        var couponGroupB = makeCouponGroup(createCouponGroup(B_쿠폰그룹));
        var promotionOptionRequestA = createPromotionOptionRequest(A_프로모션_옵션, couponGroupA.getId());
        var promotionOptionRequestB = createPromotionOptionRequest(B_프로모션_옵션, couponGroupB.getId());

        var request = createPromotionRegisterRequest(A_프로모션, List.of(promotionOptionRequestA, promotionOptionRequestB));

        // when
        promotionService.register(request);

        // then
        Promotion promotion = promotionRepository.findAll().get(0);
        assertThat(promotion).usingRecursiveComparison().ignoringFields("id", "createdAt")
                .isEqualTo(request.toEntity());
    }

    private CouponGroup makeCouponGroup(CouponGroup couponGroup) {
        return couponGroupRepository.save(couponGroup);
    }

    @DisplayName("프로모션 리스트 조회")
    @Test
    void getPromotionList() {
        // given
        var promotionA = createPromotion(A_프로모션);
        var promotionB = createPromotion(B_프로모션);
        var promotions = List.of(promotionA, promotionB);
        promotionRepository.saveAll(promotions);

        // when
        var response = promotionService.getPromotionList();

        // then
        assertThat(response).usingRecursiveComparison().ignoringCollectionOrder()
                .isEqualTo(promotions.stream().map(
                        PromotionListResponse::from).toList()
                );
    }

    @DisplayName("프로모션 상세 조회")
    @Test
    void getPromotion() {
        // given
        Promotion promotionA = makePromotion(createPromotion(A_프로모션));
        makePromotionAndPromotionOptionAndCouponGroup(promotionA);

        // when
        var response = promotionService.getPromotion(promotionA.getId());

        // then
        assertThat(response).usingRecursiveComparison().ignoringFields("promotionOptions").isEqualTo(response);
    }

    private void makePromotionAndPromotionOptionAndCouponGroup(Promotion promotionA) {
        CouponGroup couponGroupA = makeCouponGroup(createCouponGroup(A_쿠폰그룹));
        CouponGroup couponGroupB = makeCouponGroup(createCouponGroup(B_쿠폰그룹));
        couponGroupA.setPromotion(promotionA);
        couponGroupB.setPromotion(promotionA);
        PromotionOption promotionOptionA = makePromotionOption(createPromotionOption(A_프로모션_옵션, promotionA));
        PromotionOption promotionOptionB = makePromotionOption(createPromotionOption(B_프로모션_옵션, promotionA));
        makePromotionOptionCouponGroup(promotionOptionA, couponGroupA);
        makePromotionOptionCouponGroup(promotionOptionB, couponGroupA);
    }

    private PromotionOptionCouponGroup makePromotionOptionCouponGroup(PromotionOption promotionOption,
                                                                      CouponGroup couponGroup) {
        PromotionOptionCouponGroup promotionOptionCouponGroup = new PromotionOptionCouponGroup(promotionOption,
                couponGroup);
        return promotionOptionCouponGroupRepository.save(promotionOptionCouponGroup);
    }

    private Promotion makePromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    private PromotionOption makePromotionOption(PromotionOption promotionOption) {
        return promotionOptionRepository.save(promotionOption);
    }

}
