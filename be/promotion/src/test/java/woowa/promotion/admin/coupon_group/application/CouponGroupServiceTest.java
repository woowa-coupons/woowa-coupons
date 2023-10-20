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

@DisplayName("[비즈니스 로직 테스트] 쿠폰 그룹")
class CouponGroupServiceTest extends ApplicationTest {

    @Autowired
    private CouponGroupService couponGroupService;

    @DisplayName("쿠폰 그룹 목록을 조회하면 페이징 정보와 함께 id 역순으로 조회된다.")
    @Test
    void retrieveCouponGroups() {
        // given
        int couponGroupCount = 15;
        saveCouponGroupsAndCoupons(couponGroupCount);

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

    @DisplayName("쿠폰 그룹 간단 목록을 조회하면 id와 title만 조회된다.")
    @Test
    void retrieveSimpleCouponGroup() {
        // given
        int couponGroupCount = 15;
        saveCouponGroupsAndCoupons(couponGroupCount);

        // when
        var response = couponGroupService.retrieveSimpleCouponGroups();

        // then
        assertAll(
                () -> assertThat(response).hasSize(15),
                () -> assertThat(response.get(0)).hasFieldOrProperty("id"),
                () -> assertThat(response.get(0)).hasFieldOrProperty("title")
        );
    }

    private void saveCouponGroupsAndCoupons(int couponGroupCount) {
        List<CouponGroup> couponGroups = new ArrayList<>();

        IntStream.rangeClosed(1, couponGroupCount)
                .forEach(i -> couponGroups.add(
                        supportRepository.save(FixtureFactory.createCouponGroup("cg - " + i)))
                );
        IntStream.rangeClosed(1, 45)
                .forEach(i -> supportRepository.save(
                        FixtureFactory.createCoupon("coupon - " + i, couponGroups.get(i % couponGroupCount))));
    }
}
