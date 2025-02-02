package woowa.promotion.admin.coupon_group.infrastructure;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.admin.coupon_group.presentation.dto.response.CouponGroupSimpleResponse.CouponGroupSimpleDto;

public interface CouponGroupRepository extends JpaRepository<CouponGroup, Long> {

    Page<CouponGroup> findAll(Pageable pageable);

    @Query("SELECT new woowa.promotion.admin.coupon_group.presentation.dto.response.CouponGroupSimpleResponse$CouponGroupSimpleDto("
            + "couponGroup.id, couponGroup.title) FROM CouponGroup couponGroup "
            + "WHERE (:cursor = 0L OR couponGroup.id < :cursor) "
            + "ORDER BY couponGroup.id DESC"
    )
    Optional<Slice<CouponGroupSimpleDto>> findAllCouponGroupSliceDto(@Param("cursor") Long cursor, Pageable pageable);

    @Query("SELECT couponGroup FROM CouponGroup couponGroup JOIN FETCH couponGroup.coupons WHERE couponGroup.id = :couponGroupId")
    Optional<CouponGroup> findCouponGroupWithCouponsUsingFetchjoin(@Param("couponGroupId") Long counponGroupId);

}
