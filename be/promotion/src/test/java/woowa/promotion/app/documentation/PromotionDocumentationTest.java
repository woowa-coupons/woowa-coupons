package woowa.promotion.app.documentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import woowa.promotion.app.promotion.application.AppPromotionService;
import woowa.promotion.app.promotion.presentation.dto.AppPromotionResponse;
import woowa.promotion.util.DocumentationTest;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[RESTDocs] 회원 프로모션 API")
public class PromotionDocumentationTest extends DocumentationTest {

    @Autowired
    private AppPromotionService appPromotionService;

    @DisplayName("프로모션 목록 조회")
    @Test
    void retrievePromotions() throws Exception {
        // given
        given(appPromotionService.retrievePromotions()).willReturn(createAppPromotionResponse());

        // when
        var response = mockMvc
                .perform(request(HttpMethod.GET, "/app/promotions")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer abc.abc.abc"));

        // then
        var resultActions = response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").exists())
                .andExpect(jsonPath("$[*].bannerUrl").exists())
                .andExpect(jsonPath("$[*].promotionPageUrl").exists());

        // docs
        resultActions
                .andDo(document("app-promotion/retrieve",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("JWT 토큰")
                        ),
                        responseFields(
                                fieldWithPath("[].id").description("프로모션 ID"),
                                fieldWithPath("[].bannerUrl").description("프로모션 배너 URL"),
                                fieldWithPath("[].promotionPageUrl").description("프로모션 페이지 URL")
                        )
                ));
    }

    private List<AppPromotionResponse> createAppPromotionResponse() {
        return List.of(
                new AppPromotionResponse(1L, "bannerUrl", "promotionPageUrl"),
                new AppPromotionResponse(2L, "bannerUrl2", "promotionPageUrl2")
        );
    }

}
