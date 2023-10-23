package woowa.promotion.app.order.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import woowa.promotion.app.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    boolean existsByMemberId(Long memberId);
}
