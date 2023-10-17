package woowa.promotion.admin.documentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import woowa.promotion.admin.admin.application.AuthService;
import woowa.promotion.admin.admin.application.dto.request.SignupServiceRequest;
import woowa.promotion.fixture.FixtureFactory;
import woowa.promotion.util.DocumentationTest;

import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[RESTDocs] 관리자 인증 API")
public class AuthDocumentationTest extends DocumentationTest {

    @Autowired
    private AuthService authService;

    @DisplayName("관리자 회원가입")
    @Test
    void signup() throws Exception {
        // given
        SignupServiceRequest signupData = FixtureFactory.createSignupServiceRequest();
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
}
