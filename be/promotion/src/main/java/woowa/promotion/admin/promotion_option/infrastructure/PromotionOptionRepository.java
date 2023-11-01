package woowa.promotion.admin.promotion_option.infrastructure;

import java.util.List;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import woowa.promotion.admin.promotion_option.domain.PromotionOption;

public interface PromotionOptionRepository extends JpaRepository<PromotionOption, Long> {

    List<PromotionOption> findByPromotionId(Long id);
}
