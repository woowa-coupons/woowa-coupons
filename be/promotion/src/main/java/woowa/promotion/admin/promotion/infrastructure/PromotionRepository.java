package woowa.promotion.admin.promotion.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import woowa.promotion.admin.promotion.domain.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}
