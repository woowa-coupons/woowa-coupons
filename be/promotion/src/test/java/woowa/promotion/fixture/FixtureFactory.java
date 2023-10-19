package woowa.promotion.fixture;

import java.time.Instant;
import java.util.List;
import woowa.promotion.admin.admin.application.dto.request.SignInServiceRequest;
import woowa.promotion.admin.admin.application.dto.request.SignupServiceRequest;
import woowa.promotion.admin.admin.application.dto.response.SignInServiceResponse;
import woowa.promotion.admin.admin.domain.Admin;
import woowa.promotion.admin.admin.presentation.dto.request.SignInRequest;
import woowa.promotion.admin.admin.presentation.dto.request.SignupRequest;
import woowa.promotion.admin.coupon.domain.Coupon;
import woowa.promotion.admin.coupon.domain.CouponType;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.admin.coupon_group.presentation.dto.CouponGroupCreateRequest;
import woowa.promotion.admin.coupon_group.presentation.dto.CouponGroupCreateRequest.CouponDto;
import woowa.promotion.app.member.domain.Member;

public class FixtureFactory {

    public static Member createMember(UserFixture userFixture, String password) {
        return new Member(userFixture.getNickname(), userFixture.getEmail(), password);
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

    public static Admin createAdmin(String encryptedPassword) {
        return Admin.of("브루니", "bruni@woowa.com", encryptedPassword);
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

    public static CouponGroup createCouponGroup(String couponGroupTitle) {
        return CouponGroup.builder()
                .title(couponGroupTitle)
                .startedAt(Instant.now())
                .finishedAt(Instant.now().plusSeconds(10 * 24 * 60 * 60))
                .adminNickname("admin")
                .build();
    }

    public static Coupon createCoupon(String couponTitle, CouponGroup couponGroup) {
        return Coupon.builder()
                .title(couponTitle)
                .type(CouponType.FIXED)
                .discount(1000)
                .initialQuantity(100)
                .couponGroup(couponGroup)
                .build();
    }

}
