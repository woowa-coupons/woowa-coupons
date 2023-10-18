package woowa.promotion.admin.coupon_group.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import woowa.promotion.admin.admin.domain.Admin;
import woowa.promotion.admin.coupon_group.application.CouponGroupService;
import woowa.promotion.admin.coupon_group.presentation.dto.CouponGroupCreateRequest;
import woowa.promotion.global.resolver.Authentication;

@RequiredArgsConstructor
@RequestMapping("/admin/coupon-groups")
@RestController
public class CouponGroupController {

    private final CouponGroupService couponGroupService;

    @PostMapping
    public ResponseEntity<Void> createCouponGroups(
            @RequestBody CouponGroupCreateRequest request,
            @Authentication Admin loginAdmin
    ) {
        couponGroupService.saveCouponGroup(request, loginAdmin);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}