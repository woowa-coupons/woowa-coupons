package woowa.promotion.util;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import woowa.promotion.admin.admin.application.AdminService;
import woowa.promotion.admin.admin.domain.Admin;
import woowa.promotion.admin.admin.presentation.AuthController;
import woowa.promotion.global.interceptor.AuthInterceptor;
import woowa.promotion.global.resolver.AuthArgumentResolver;

@MockBean({
        AdminService.class
})
@WebMvcTest({
        AuthController.class
})
@AutoConfigureRestDocs
public abstract class DocumentationTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected AuthInterceptor authInterceptor;

    @MockBean
    protected AuthArgumentResolver authArgumentResolver;

    @BeforeEach
    void setUp() {
        given(authInterceptor.preHandle(any(), any(), any())).willReturn(true);
        given(authArgumentResolver.resolveArgument(any(), any(), any(), any()))
                .willReturn(Admin.of("nickname", "email", "password"));
    }
}