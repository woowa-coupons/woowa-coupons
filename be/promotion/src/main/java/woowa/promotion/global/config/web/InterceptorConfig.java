package woowa.promotion.global.config.web;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import woowa.promotion.global.interceptor.AuthInterceptor;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private static final List<String> WHILTE_LIST = List.of(
            "/app/auth/sign-up",
            "/app/auth/sign-in",
            "/app/promotions",
            "/admin/auth/sign-up",
            "/admin/auth/sign-in"
    );

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(WHILTE_LIST);
    }
}
