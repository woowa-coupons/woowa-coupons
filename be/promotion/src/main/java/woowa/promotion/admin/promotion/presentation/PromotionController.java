package woowa.promotion.admin.promotion.presentation;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import woowa.promotion.admin.promotion.application.PromotionService;
import woowa.promotion.admin.promotion.application.dto.request.PromotionRegisterRequest;
import woowa.promotion.admin.promotion.application.dto.response.PromotionDetailResponse;
import woowa.promotion.admin.promotion.application.dto.response.PromotionListResponse;

@RequiredArgsConstructor
@RequestMapping("/admin/promotions")
@RestController
public class PromotionController {

    private final PromotionService promotionService;

    @PostMapping("")
    public ResponseEntity<Void> register(
            @RequestBody PromotionRegisterRequest request
    ) {
        promotionService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    public ResponseEntity<List<PromotionListResponse>> getPromotionList() {
        var response = promotionService.getPromotionList();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{promotionId}")
    public ResponseEntity<PromotionDetailResponse> getPromotion(
            @PathVariable Long promotionId
    ) {
        var response = promotionService.getPromotion(promotionId);
        return ResponseEntity.ok().body(response);
    }
}
