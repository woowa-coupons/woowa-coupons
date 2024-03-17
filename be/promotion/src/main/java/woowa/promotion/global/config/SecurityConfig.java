package woowa.promotion.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import woowa.promotion.global.security.hash.PasswordEncoder;
import woowa.promotion.global.security.hash.SHA256;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SHA256();
    }

}
