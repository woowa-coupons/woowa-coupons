package woowa.promotion.admin.promotion.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woowa.promotion.admin.promotion.application.PromotionService;
import woowa.promotion.admin.promotion.application.dto.request.PromotionRegisterRequest;
import woowa.promotion.admin.promotion.application.dto.response.PromotionDetailResponse;
import woowa.promotion.admin.promotion.application.dto.response.PromotionListResponse;
import woowa.promotion.global.domain.page.CustomPage;

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
    public ResponseEntity<CustomPage<PromotionListResponse>> getPromotionList(
            @PageableDefault(
                    sort = "id",
                    direction = Sort.Direction.DESC,
                    page = 1
            ) Pageable pageable
    ) {
        var response = promotionService.getPromotionList(pageable);
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
