package woowa.promotion.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import woowa.promotion.app.member.security.hash.PasswordEncoder;
import woowa.promotion.app.member.security.hash.SHA256;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SHA256();
    }
}
