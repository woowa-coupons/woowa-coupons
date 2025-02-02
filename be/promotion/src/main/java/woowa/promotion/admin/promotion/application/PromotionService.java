package woowa.promotion.admin.promotion.application;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowa.promotion.admin.coupon_group.domain.CouponGroup;
import woowa.promotion.admin.coupon_group.infrastructure.CouponGroupRepository;
import woowa.promotion.admin.promotion.application.dto.request.PromotionRegisterRequest;
import woowa.promotion.admin.promotion.application.dto.request.PromotionRegisterRequest.PromotionOptionRequest;
import woowa.promotion.admin.promotion.application.dto.response.PromotionDetailResponse;
import woowa.promotion.admin.promotion.application.dto.response.PromotionListResponse;
import woowa.promotion.admin.promotion.domain.Promotion;
import woowa.promotion.admin.promotion.infrastructure.PromotionRepository;
import woowa.promotion.admin.promotion_option.domain.PromotionOption;
import woowa.promotion.admin.promotion_option.infrastructure.PromotionOptionRepository;
import woowa.promotion.admin.promotion_option_coupon_group.domain.PromotionOptionCouponGroup;
import woowa.promotion.admin.promotion_option_coupon_group.infrastructure.PromotionOptionCouponGroupRepository;
import woowa.promotion.global.domain.page.CustomPage;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.exception.domain.CouponGroupException;
import woowa.promotion.global.exception.domain.PromotionException;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final PromotionOptionRepository promotionOptionRepository;
    private final CouponGroupRepository couponGroupRepository;
    private final PromotionOptionCouponGroupRepository promotionOptionCouponGroupRepository;

    @Transactional
    public void register(PromotionRegisterRequest request) {
        Promotion promotion = savePromotion(request);

        request.promotionOptions().forEach(promotionOptionRequest ->
                savePromotionOptionWithCouponGroup(promotionOptionRequest, promotion));
    }

    private Promotion savePromotion(PromotionRegisterRequest request) {
        return promotionRepository.save(request.toEntity());
    }

    private void savePromotionOptionWithCouponGroup(PromotionOptionRequest promotionOptionRequest,
                                                    Promotion promotion) {
        CouponGroup couponGroup = couponGroupRepository.findById(promotionOptionRequest.couponGroupId())
                .orElseThrow(() -> new ApiException(CouponGroupException.NOT_FOUND));

        PromotionOption promotionOption = savePromotionOption(promotionOptionRequest, promotion);

        couponGroup.setPromotion(promotion);

        promotionOptionCouponGroupRepository.save(new PromotionOptionCouponGroup(promotionOption, couponGroup));
    }

    private PromotionOption savePromotionOption(PromotionOptionRequest promotionOptionRequest, Promotion promotion) {
        return promotionOptionRepository.save(promotionOptionRequest.toEntity(promotion));
    }

    public CustomPage<PromotionListResponse> getPromotionList(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(
                Math.max(pageable.getPageNumber() - 1, 0),  // page 쿼리 파라미터가 0으로 넘어가거나 그 이하일 경우
                pageable.getPageSize(),
                pageable.getSort()
        );
        Page<Promotion> pagedPromotions = promotionRepository.findAll(pageRequest);

        return new CustomPage<>(
                pagedPromotions.getContent().stream()
                        .map(PromotionListResponse::from)
                        .toList(),
                new CustomPage.Paging(
                        pageable.getPageNumber(),
                        pagedPromotions.getTotalPages(),
                        pagedPromotions.getTotalElements(),
                        pagedPromotions.getContent().size()
                )
        );
    }

    public PromotionDetailResponse getPromotion(Long promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new ApiException(PromotionException.NOT_FOUND));

        List<PromotionOption> promotionOptions = promotionOptionRepository.findByPromotionId(promotionId);
        List<CouponGroup> couponGroups = promotionOptions.stream()
                .map(this::getPromotionOptionCouponGroups)
                .map(list -> getMatchingCouponGroup(list, promotionId))
                .collect(Collectors.toList());

        return PromotionDetailResponse.of(promotion, promotionOptions, couponGroups);
    }

    private List<PromotionOptionCouponGroup> getPromotionOptionCouponGroups(PromotionOption promotionOption) {
        return promotionOptionCouponGroupRepository.findByPromotionOptionId(promotionOption.getId());
    }

    private CouponGroup getMatchingCouponGroup(List<PromotionOptionCouponGroup> couponGroupList, Long promotionId) {
        return couponGroupList.stream()
                .filter(promotionOptionCouponGroup -> promotionOptionCouponGroup.matchesPromotionId(promotionId))
                .map(PromotionOptionCouponGroup::getCouponGroup)
                .findFirst()
                .orElseThrow(() -> new ApiException(CouponGroupException.NOT_FOUND));
    }
}
