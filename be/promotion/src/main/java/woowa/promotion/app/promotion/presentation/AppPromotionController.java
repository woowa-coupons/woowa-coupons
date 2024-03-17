package woowa.promotion.app.promotion.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import woowa.promotion.app.promotion.application.AppPromotionService;
import woowa.promotion.app.promotion.presentation.dto.AppPromotionResponse;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/app/promotions")
@RestController
public class AppPromotionController {

    private final AppPromotionService appPromotionService;

    @GetMapping
    public ResponseEntity<List<AppPromotionResponse>> retrievePromotions() {
        return ResponseEntity.ok(appPromotionService.retrievePromotions());
    }

}
