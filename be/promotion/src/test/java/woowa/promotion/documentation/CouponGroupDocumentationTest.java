package woowa.promotion.documentation;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import woowa.promotion.admin.coupon_group.application.CouponGroupService;
import woowa.promotion.admin.coupon_group.presentation.dto.response.CouponGroupDetailResponse;
import woowa.promotion.admin.coupon_group.presentation.dto.response.CouponGroupDetailResponse.CouponGroupCouponResponse;
import woowa.promotion.admin.coupon_group.presentation.dto.response.CouponGroupSimpleResponse;
import woowa.promotion.admin.coupon_group.presentation.dto.response.CouponGroupSimpleResponse.CouponGroupSimpleDto;
import woowa.promotion.admin.coupon_group.presentation.dto.response.CouponGroupsResponse;
import woowa.promotion.global.domain.page.CustomPage;
import woowa.promotion.util.DocumentationTest;

@DisplayName("[RESTDocs] 쿠폰 그룹 API")
public class CouponGroupDocumentationTest extends DocumentationTest {

    @Autowired
    private CouponGroupService couponGroupService;

    private List<CouponGroupsResponse> createCouponGroupsResponse() {
        List<CouponGroupsResponse> ret = new ArrayList<>();
        for (long i = 15; i >= 1; i--) {
            ret.add(new CouponGroupsResponse(
                    i,
                    "쿠폰 그룹 제목 - " + i,
                    true,
                    List.of("쿠폰 제목1", "쿠폰 제목2"),
                    100,
                    100,
                    "관리자"
            ));
        }
        return ret;
    }

    @DisplayName("쿠폰 그룹 목록 조회")
    @Test
    void retrieveCouponGroups() throws Exception {
        // given
        given(couponGroupService.retrieveCouponGroups(any()))
                .willReturn(new CustomPage<>(
                        createCouponGroupsResponse(),
                        new CustomPage.Paging(
                                1,
                                2,
                                15,
                                10
                        ))
                );

        // when
        var response = mockMvc.perform(request(HttpMethod.GET, "/admin/coupon-groups")
                .queryParam("page", "1")
                .queryParam("size", "10")
                .header(HttpHeaders.AUTHORIZATION, "Bearer abc.abc.abc"));

        // then
        var resultActions = response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[*].id").exists())
                .andExpect(jsonPath("$.data[*].title").exists())
                .andExpect(jsonPath("$.data[*].isRandom").exists())
                .andExpect(jsonPath("$.data[*].couponTitles").isArray())
                .andExpect(jsonPath("$.data[*].couponTitles[*]").exists())
                .andExpect(jsonPath("$.data[*].totalRemainQuantity").exists())
                .andExpect(jsonPath("$.data[*].totalInitialQuantity").exists())
                .andExpect(jsonPath("$.data[*].createdBy").exists())
                .andExpect(jsonPath("$.paging.currentPage").isNumber())
                .andExpect(jsonPath("$.paging.totalPages").isNumber())
                .andExpect(jsonPath("$.paging.totalElements").isNumber())
                .andExpect(jsonPath("$.paging.size").isNumber());

        // docs
        resultActions
                .andDo(document("admin/coupon-groups/retrieve-coupon-groups",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("page").description("페이지 번호"),
                                parameterWithName("size").description("페이지 크기")
                        ),
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("JWT 인증 토큰")
                        ),
                        responseFields(
                                fieldWithPath("data").description("쿠폰 그룹 목록"),
                                fieldWithPath("data[].id").description("쿠폰 그룹 ID"),
                                fieldWithPath("data[].title").description("쿠폰 그룹 제목"),
                                fieldWithPath("data[].isRandom").description("쿠폰 그룹의 쿠폰 발급 방식"),
                                fieldWithPath("data[].couponTitles").description("쿠폰 그룹에 포함된 쿠폰 제목 목록"),
                                fieldWithPath("data[].totalRemainQuantity").description("쿠폰 그룹에 포함된 쿠폰의 잔여 수량 총합"),
                                fieldWithPath("data[].totalInitialQuantity").description("쿠폰 그룹에 포함된 쿠폰의 초기 수량 총합"),
                                fieldWithPath("data[].createdBy").description("쿠폰 그룹을 생성한 관리자"),
                                fieldWithPath("paging.currentPage").description("현재 페이지"),
                                fieldWithPath("paging.totalPages").description("총 페이지 수"),
                                fieldWithPath("paging.totalElements").description("총 요소 수"),
                                fieldWithPath("paging.size").description("페이지 크기")
                        )
                ));
    }

    @DisplayName("쿠폰 그룹 간단 목록 조회")
    @Test
    void retrieveSimpleCouponGroups() throws Exception {
        // given
        given(couponGroupService.retrieveSimpleCouponGroups(0L, 2))
                .willReturn(new CouponGroupSimpleResponse(
                        List.of(
                                new CouponGroupSimpleDto(1L, "쿠폰 그룹 제목 - 1"),
                                new CouponGroupSimpleDto(2L, "쿠폰 그룹 제목 - 2")

                        ),
                        false
                ));

        // when
        var response = mockMvc.perform(request(HttpMethod.GET, "/admin/coupon-groups/summary?size=2")
                .header(HttpHeaders.AUTHORIZATION, "Bearer abc.abc.abc"));

        // then
        var resultActions = response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.couponGroups").isArray())
                .andExpect(jsonPath("$.couponGroups[*].id").exists())
                .andExpect(jsonPath("$.couponGroups[*].title").exists())
                .andExpect(jsonPath("$.hasNext").exists());

        // docs
        resultActions
                .andDo(document("admin/coupon-groups/retrieve-simple-coupon-groups",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("JWT 인증 토큰")
                        ),
                        responseFields(
                                fieldWithPath("couponGroups[]").description("쿠폰 그룹 간단 목록"),
                                fieldWithPath("couponGroups[].id").description("쿠폰 그룹 ID"),
                                fieldWithPath("couponGroups[].title").description("쿠폰 그룹 제목"),
                                fieldWithPath("hasNext").description("다음 페이지 존재 여부")
                        )
                ));
    }

    @DisplayName("쿠폰 그룹 상세 조회")
    @Test
    void retrieveDetailCouponGroups() throws Exception {
        // given
        given(couponGroupService.retrieveDetailCouponGroup(any()))
                .willReturn(new CouponGroupDetailResponse(
                                "쿠폰 그룹 제목 - 1",
                                true,
                                Instant.now(),
                                Instant.now().plusSeconds(10 * 24 * 60 * 60),
                                List.of(new CouponGroupCouponResponse("쿠폰 - 1", "FIXED", 1000, 100))
                        )
                );

        // when
        var response = mockMvc.perform(request(HttpMethod.GET, "/admin/coupon-groups/1")
                .header(HttpHeaders.AUTHORIZATION, "Bearer abc.abc.abc"));

        // then
        var resultActions = response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.isRandom").exists())
                .andExpect(jsonPath("$.startedAt").exists())
                .andExpect(jsonPath("$.finishedAt").exists())
                .andExpect(jsonPath("$.coupons").isArray())
                .andExpect(jsonPath("$.coupons[*].title").exists())
                .andExpect(jsonPath("$.coupons[*].type").exists())
                .andExpect(jsonPath("$.coupons[*].discount").exists())
                .andExpect(jsonPath("$.coupons[*].initialQuantity").exists());

        // docs
        resultActions
                .andDo(document("admin/coupon-groups/retrieve-detail-coupon-groups",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("JWT 인증 토큰")
                        ),
                        responseFields(
                                fieldWithPath(".title").description("쿠폰 그룹"),
                                fieldWithPath(".isRandom").description("쿠폰 그룹의 쿠폰 발급 방식"),
                                fieldWithPath(".startedAt").description("쿠폰 그룹 시작 시간"),
                                fieldWithPath(".finishedAt").description("쿠폰 그룹 종료 시간"),
                                fieldWithPath("coupons[].title").description("쿠폰 제목"),
                                fieldWithPath("coupons[].type").description("쿠폰 할인 종류"),
                                fieldWithPath("coupons[].discount").description("쿠폰 할인 discount"),
                                fieldWithPath("coupons[].initialQuantity").description("쿠폰 초기 수량")
                        )
                ));
    }
}
