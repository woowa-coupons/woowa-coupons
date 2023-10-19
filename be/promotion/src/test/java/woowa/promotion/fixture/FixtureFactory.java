package woowa.promotion.fixture;

import java.time.Instant;
import java.util.List;
import woowa.promotion.admin.admin.application.dto.request.SignInServiceRequest;
import woowa.promotion.admin.admin.application.dto.request.SignupServiceRequest;
import woowa.promotion.admin.admin.application.dto.response.SignInServiceResponse;
import woowa.promotion.admin.admin.domain.Admin;
import woowa.promotion.admin.admin.presentation.dto.request.SignInRequest;
import woowa.promotion.admin.admin.presentation.dto.request.SignupRequest;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.admin.coupon_group.presentation.dto.CouponGroupCreateRequest;
import woowa.promotion.admin.coupon_group.presentation.dto.CouponGroupCreateRequest.CouponDto;
import woowa.promotion.admin.promotion.application.dto.request.PromotionRegisterRequest;
import woowa.promotion.admin.promotion.application.dto.request.PromotionRegisterRequest.PromotionOptionRequest;
import woowa.promotion.admin.promotion.domain.ProgressStatus;
import woowa.promotion.admin.promotion.domain.Promotion;
import woowa.promotion.admin.promotion_option.domain.MemberType;
import woowa.promotion.admin.promotion_option.domain.PromotionOption;
import woowa.promotion.app.member.domain.Member;

public class FixtureFactory {

    public static Member createMember(UserFixture userFixture, String password) {
        return new Member(userFixture.getNickname(), userFixture.getEmail(), password);
    }

    public static PromotionOption createPromotionOption(PromotionOptionFixture promotionOptionFixture,
                                                        Promotion promotion) {
        return new PromotionOption(promotionOptionFixture.getLastOrderAt(), promotionOptionFixture.getRandom(),
                promotion,
                MemberType.from(promotionOptionFixture.getMemberType()));
    }

    public static SignupRequest createSignupRequest() {
        return new SignupRequest(
                "bruni@woowa.com",
                "브루니",
                "1234"
        );
    }

    public static SignupServiceRequest createSignupServiceRequest() {
        return new SignupServiceRequest(
                "bruni@woowa.com",
                "브루니",
                "1234"
        );
    }

    public static SignInRequest createSignInRequest() {
        return new SignInRequest(
                "bruni@woowa.com",
                "1234"
        );
    }

    public static SignInServiceRequest createSignInServiceRequest() {
        return new SignInServiceRequest(
                "bruni@woowa.com",
                "1234"
        );
    }

    public static SignInServiceResponse createSignInServiceResponse() {
        return new SignInServiceResponse("accessToken");
    }

    public static Admin createAdmin() {
        return Admin.of("브루니", "bruni@woowa.com", "1234");
    }

    public static Promotion createPromotion(PromotionFixture promotionFixture) {
        return new Promotion(promotionFixture.getTitle(), promotionFixture.getContent(), promotionFixture.getBanner(),
                promotionFixture.getStartedAt(), promotionFixture.getFinishedAt(),
                ProgressStatus.valueOf(promotionFixture.getProgressStatus()), promotionFixture.getPromotionPageUrl(),
                promotionFixture.isDisplay());
    }

    public static CouponGroup createCouponGroup(CouponGroupFixture couponGroupFixture) {
        return CouponGroup.builder().title(couponGroupFixture.getTitle()).finishedAt(couponGroupFixture.getFinishedAt())
                .startedAt(couponGroupFixture.getStartedAt()).adminNickname("admin").build();
    }

    public static PromotionOptionRequest createPromotionOptionRequest(PromotionOptionFixture promotionOptionFixture,
                                                                      Long couponGroupId) {
        return new PromotionOptionRequest(promotionOptionFixture.getMemberType(),
                promotionOptionFixture.getLastOrderAt(), promotionOptionFixture.getRandom(), couponGroupId);
    }


    public static PromotionRegisterRequest createPromotionRegisterRequest(PromotionFixture promotionFixture,
                                                                          List<PromotionOptionRequest> promotionOptions) {
        return new PromotionRegisterRequest(
                promotionFixture.getTitle(),
                promotionFixture.getContent(),
                promotionFixture.getBanner(),
                promotionFixture.getStartedAt(),
                promotionFixture.getFinishedAt(),
                promotionFixture.getPromotionPageUrl(),
                promotionFixture.isDisplay(),
                promotionFixture.getProgressStatus(),
                promotionOptions);
    }


    public static CouponGroupCreateRequest createCouponGroupCreateRequest() {
        return new CouponGroupCreateRequest(
                "쿠폰 그룹 제목",
                Instant.parse("2023-10-06T14:30:00Z"),
                Instant.parse("2023-10-06T14:30:00Z"),
                List.of(
                        new CouponDto("쿠폰 제목", "fixed", 1000, 100)
                )
        );
    }
}
