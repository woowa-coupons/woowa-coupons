package woowa.promotion.app.order.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import woowa.promotion.app.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders WHERE member_id = :memberId ORDER BY id DESC LIMIT 1",
            nativeQuery = true)
    Order findLastOneByMemberId(@Param("memberId") Long memberId);
}
