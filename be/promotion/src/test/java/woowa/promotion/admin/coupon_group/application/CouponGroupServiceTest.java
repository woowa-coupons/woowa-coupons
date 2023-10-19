package woowa.promotion.admin.coupon_group.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.fixture.FixtureFactory;
import woowa.promotion.util.ApplicationTest;

class CouponGroupServiceTest extends ApplicationTest {

    @Autowired
    private CouponGroupService couponGroupService;

    @DisplayName("쿠폰 그룹 목록을 조회하면 페이징 정보와 함께 id 역순으로 조회된다.")
    @Test
    void retrieveCouponGroups() {
        // given
        List<CouponGroup> couponGroups = new ArrayList<>();
        IntStream.rangeClosed(1, 15)
                .forEach(i -> couponGroups.add(
                        supportRepository.save(FixtureFactory.createCouponGroup("cg - " + i)))
                );
        IntStream.rangeClosed(1, 45)
                .forEach(i -> supportRepository.save(
                        FixtureFactory.createCoupon("coupon - " + i, couponGroups.get(i % 15))));

        // when
        var response = couponGroupService.retrieveCouponGroups(PageRequest.of(1, 10, Direction.DESC, "id"));

        // then
        assertAll(
                () -> assertThat(response.data()).hasSize(10),
                () -> assertThat(response.data().get(0).title()).isEqualTo("cg - 15"),
                () -> assertThat(response.paging().currentPage()).isEqualTo(1),
                () -> assertThat(response.paging().totalPages()).isEqualTo(2),
                () -> assertThat(response.paging().totalElements()).isEqualTo(15),
                () -> assertThat(response.paging().size()).isEqualTo(10)
        );
    }
}
