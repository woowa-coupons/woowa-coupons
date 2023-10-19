package woowa.promotion.admin.coupon_group.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowa.promotion.admin.admin.domain.Admin;
import woowa.promotion.admin.coupon.domain.Coupon;
import woowa.promotion.admin.coupon.infrastructure.CouponRepository;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.admin.coupon_group.infrastructure.CouponGroupRepository;
import woowa.promotion.admin.coupon_group.presentation.dto.CouponGroupCreateRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CouponGroupService {

    private final CouponGroupRepository couponGroupRepository;
    private final CouponRepository couponRepository;

    @Transactional
    public void saveCouponGroup(CouponGroupCreateRequest request, Admin loginAdmin) {
        CouponGroup couponGroup = request.toCouponGroup(loginAdmin.getNickname());
        couponGroupRepository.save(couponGroup);

        List<Coupon> coupons = request.toCoupons(couponGroup);
        couponRepository.saveAll(coupons);
    }
}
