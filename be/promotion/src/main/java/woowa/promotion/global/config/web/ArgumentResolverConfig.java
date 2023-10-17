package woowa.promotion.global.config.web;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import woowa.promotion.global.resolver.AuthArgumentResolver;

@Configuration
@RequiredArgsConstructor
public class ArgumentResolverConfig implements WebMvcConfigurer {

    private final AuthArgumentResolver authArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authArgumentResolver);
    }
}
