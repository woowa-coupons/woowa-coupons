package woowa.promotion.fixture;

import woowa.promotion.admin.coupon.domain.CouponType;

public enum CouponFixture {

    추석_쿠폰_신규("추석 쿠폰 신규", 1000, CouponType.FIXED.name(), 1000, 1000),
    추석_쿠폰_랜덤_1000("추석 쿠폰 랜덤", 1000, CouponType.FIXED.name(), 1000, 1000),
    추석_쿠폰_랜덤_2000("추석 쿠폰 랜덤", 1000, CouponType.FIXED.name(), 1000, 1000),
    추석_쿠폰_랜덤_3000("추석 쿠폰 랜덤", 1000, CouponType.FIXED.name(), 1000, 1000);


    private final String title;
    private final Integer discount;
    private final String couponType;
    private final Integer initialQuantity;
    private final Integer remainQuantity;

    CouponFixture(String title, Integer discount, String couponType, Integer initialQuantity,
                  Integer remainQuantity) {
        this.title = title;
        this.discount = discount;
        this.couponType = couponType;
        this.initialQuantity = initialQuantity;
        this.remainQuantity = remainQuantity;
    }

    public String getTitle() {
        return title;
    }

    public Integer getDiscount() {
        return discount;
    }

    public String getCouponType() {
        return couponType;
    }

    public Integer getInitialQuantity() {
        return initialQuantity;
    }

    public Integer getRemainQuantity() {
        return remainQuantity;
    }
}
