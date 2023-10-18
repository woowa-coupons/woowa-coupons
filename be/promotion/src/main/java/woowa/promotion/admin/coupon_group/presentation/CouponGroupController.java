package woowa.promotion.admin.coupon_group.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import woowa.promotion.admin.coupon_group.application.CouponGroupService;
import woowa.promotion.admin.coupon_group.presentation.dto.CouponGroupCreateRequest;

@RequiredArgsConstructor
@RequestMapping("/admin/coupon-groups")
@RestController
public class CouponGroupController {

    private final CouponGroupService couponGroupService;

    @PostMapping
    public ResponseEntity<Void> createCouponGroups (
            @RequestBody CouponGroupCreateRequest request
    ) {
        couponGroupService.saveCouponGroup(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
