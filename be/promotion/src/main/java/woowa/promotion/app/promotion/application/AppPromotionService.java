package woowa.promotion.app.promotion.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowa.promotion.admin.promotion.infrastructure.PromotionRepository;
import woowa.promotion.app.promotion.presentation.dto.AppPromotionResponse;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AppPromotionService {

    private final PromotionRepository promotionRepository;

    public List<AppPromotionResponse> retrievePromotions() {
        return promotionRepository.findAllByIsDisplayTrue();
    }

}
