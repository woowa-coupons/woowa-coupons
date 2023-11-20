package woowa.promotion.documentation;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import woowa.promotion.admin.admin.application.AdminAuthService;
import woowa.promotion.admin.admin.presentation.dto.request.SignInRequest;
import woowa.promotion.util.DocumentationTest;
import woowa.promotion.util.fixture.FixtureFactory;

@DisplayName("[RESTDocs] 관리자 인증 API")
public class AuthDocumentationTest extends DocumentationTest {

    @Autowired
    private AdminAuthService adminAuthService;

    @DisplayName("관리자 회원가입")
    @Test
    void signup() throws Exception {
        // given
        var signupData = FixtureFactory.createSignupServiceRequest();
        willDoNothing().given(adminAuthService).signUp(signupData);

        // when
        var response = mockMvc.perform(post("/admin/auth/sign-up")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(signupData)));

        // then
        var resultActions = response.andExpect(status().isCreated());

        // docs
        resultActions
                .andDo(document("admin/auth/signup",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("email").description("이메일"),
                                fieldWithPath("nickname").description("닉네임"),
                                fieldWithPath("password").description("비밀번호")
                        )
                ));
    }

    @DisplayName("관리자 로그인")
    @Test
    void signIn() throws Exception {
        // given
        var signInData = FixtureFactory.createSignInRequest();
        given(adminAuthService.signIn(any(SignInRequest.class))).willReturn(FixtureFactory.createSignInServiceResponse());

        // when
        var response = mockMvc.perform(post("/admin/auth/sign-in")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(signInData)));

        // then
        var resultActions = response.andExpect(status().isOk())
                .andExpect(jsonPath("accessToken").isNotEmpty());

        // docs
        resultActions
                .andDo(document("admin/auth/signin",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("email").description("로그인 이메일"),
                                fieldWithPath("password").description("로그인 비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("accessToken").description("액세스 토큰")
                        )
                ));
    }
}
