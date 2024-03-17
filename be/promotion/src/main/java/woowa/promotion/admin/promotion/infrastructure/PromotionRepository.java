package woowa.promotion.admin.promotion.infrastructure;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import woowa.promotion.admin.promotion.domain.Promotion;
import woowa.promotion.app.promotion.presentation.dto.AppPromotionResponse;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Query("SELECT new woowa.promotion.app.promotion.presentation.dto.AppPromotionResponse(" +
            "promotion.id, promotion.bannerUrl, promotion.promotionPageUrl" +
            ") FROM Promotion promotion WHERE promotion.isDisplay = true")
    List<AppPromotionResponse> findAllByIsDisplayTrue();

    Page<Promotion> findAll(Pageable pageable);
}
