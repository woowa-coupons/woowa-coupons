package woowa.promotion.documentation;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.app.member_coupon.application.MemberCouponService;
import woowa.promotion.app.member_coupon.presentation.dto.CouponIssueRequest;
import woowa.promotion.util.DocumentationTest;

@DisplayName("[REST Docs] 회원 쿠폰")
public class MemberCouponDocumentationTest extends DocumentationTest {

    @Autowired
    private MemberCouponService memberCouponService;

    @DisplayName("쿠폰 발급")
    @Test
    void issueCoupon() throws Exception {
        // given
        willDoNothing().given(memberCouponService).issueCoupon(any(CouponIssueRequest.class), any(Member.class));

        // when
        var response = mockMvc.perform(post("/app/member-coupons")
                .header(HttpHeaders.AUTHORIZATION, "Bearer abc.abc.abc")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new CouponIssueRequest(1L))));

        // then
        var resultActions = response
                .andExpect(status().isCreated());

        // docs
        resultActions.andDo(document("member-coupon/issue",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                        headerWithName(HttpHeaders.AUTHORIZATION).description("JWT 인증 토큰")
                ),
                requestFields(
                        fieldWithPath("promotionId").description("프로모션 ID")
                )
        ));
    }
}
