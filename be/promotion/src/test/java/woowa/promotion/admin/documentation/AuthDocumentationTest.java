package woowa.promotion.admin.documentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import woowa.promotion.admin.admin.application.AuthService;
import woowa.promotion.admin.admin.application.dto.request.SignInServiceRequest;
import woowa.promotion.fixture.FixtureFactory;
import woowa.promotion.util.DocumentationTest;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[RESTDocs] 관리자 인증 API")
public class AuthDocumentationTest extends DocumentationTest {

    @Autowired
    private AuthService authService;

    @DisplayName("관리자 회원가입")
    @Test
    void signup() throws Exception {
        // given
        var signupData = FixtureFactory.createSignupServiceRequest();
        willDoNothing().given(authService).signup(signupData);

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
        given(authService.signIn(any(SignInServiceRequest.class))).willReturn(FixtureFactory.createSignInServiceResponse());

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
