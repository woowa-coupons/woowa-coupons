package woowa.promotion.admin.coupon_group.application;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowa.promotion.admin.admin.domain.Admin;
import woowa.promotion.admin.coupon.domain.Coupon;
import woowa.promotion.admin.coupon.infrastructure.CouponRepository;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.admin.coupon_group.infrastructure.CouponGroupRepository;
import woowa.promotion.admin.coupon_group.presentation.dto.CouponGroupCreateRequest;
import woowa.promotion.admin.coupon_group.presentation.dto.response.CouponGroupsResponse;
import woowa.promotion.global.domain.page.CustomPage;
import woowa.promotion.global.domain.page.Paging;

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

    public CustomPage<CouponGroupsResponse> retrieveCouponGroups(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(),
                pageable.getSort());
        Page<CouponGroup> couponGroups = couponGroupRepository.findAll(pageRequest);

        List<CouponGroupsResponse> couponGroupsResponses = couponGroups.stream()
                .map(couponGroup -> CouponGroupsResponse.of(couponGroup, couponGroup.getCoupons()))
                .collect(Collectors.toList());

        return new CustomPage<>(
                couponGroupsResponses,
                new Paging(
                        pageable.getPageNumber(),
                        couponGroups.getTotalPages(),
                        couponGroups.getTotalElements(),
                        pageable.getPageSize()
                )
        );
    }
}
