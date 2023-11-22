package woowa.promotion.admin.coupon_group.presentation;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import woowa.promotion.admin.auth.domain.Admin;
import woowa.promotion.admin.coupon_group.application.CouponGroupService;
import woowa.promotion.admin.coupon_group.presentation.dto.request.CouponGroupCreateRequest;
import woowa.promotion.admin.coupon_group.presentation.dto.response.CouponGroupDetailResponse;
import woowa.promotion.admin.coupon_group.presentation.dto.response.CouponGroupSimpleResponse;
import woowa.promotion.admin.coupon_group.presentation.dto.response.CouponGroupsResponse;
import woowa.promotion.global.domain.page.CustomPage;
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

    @GetMapping
    public ResponseEntity<CustomPage<CouponGroupsResponse>> retrieveCouponGroups(
            @PageableDefault(
                    sort = "id",
                    direction = Sort.Direction.DESC,
                    page = 1
            ) Pageable pageable
    ) {
        return ResponseEntity.ok(couponGroupService.retrieveCouponGroups(pageable));
    }

    @GetMapping("/summary")
    public ResponseEntity<List<CouponGroupSimpleResponse>> retrieveSimpleCouponGroups() {
        return ResponseEntity.ok(couponGroupService.retrieveSimpleCouponGroups());
    }

    @GetMapping("/{couponGroupId}")
    public ResponseEntity<CouponGroupDetailResponse> retrieveDetailCouponCroup(
            @PathVariable Long couponGroupId
    ) {
        return ResponseEntity.ok(couponGroupService.retrieveDetailCouponGroup(couponGroupId));
    }
}
