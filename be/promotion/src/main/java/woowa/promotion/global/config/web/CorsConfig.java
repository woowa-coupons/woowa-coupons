package woowa.promotion.global.config.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${front.local.url}")
    private String frontLocalUrl;

    @Value("${front.server.url}")
    private String frontServerUrl;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(frontLocalUrl, frontServerUrl)
                .allowedMethods("OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE");
    }
}
