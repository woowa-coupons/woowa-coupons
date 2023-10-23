package woowa.promotion.admin.coupon_group.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.admin.coupon_group.presentation.dto.response.CouponGroupSimpleResponse;

import java.util.List;

public interface CouponGroupRepository extends JpaRepository<CouponGroup, Long> {

    Page<CouponGroup> findAll(Pageable pageable);

    @Query("SELECT new woowa.promotion.admin.coupon_group.presentation.dto.response.CouponGroupSimpleResponse(" +
            "couponGroup.id, couponGroup.title" +
            ") FROM CouponGroup couponGroup")
    List<CouponGroupSimpleResponse> findAllCouponGroupSimpleResponse();

}
