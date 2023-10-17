package woowa.promotion.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import woowa.promotion.admin.admin.application.AuthService;
import woowa.promotion.admin.admin.presentation.AuthController;

@MockBean({
        AuthService.class
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

}
