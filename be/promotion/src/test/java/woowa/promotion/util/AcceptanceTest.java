package woowa.promotion.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import woowa.promotion.acceptance.SupportRepository;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.app.member.security.hash.PasswordEncoder;
import woowa.promotion.fixture.FixtureFactory;

@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public abstract class AcceptanceTest {

    @Autowired
    protected SupportRepository supportRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    protected Member makeMember() throws Exception {
        String password = passwordEncoder.encrypt("password");
        return supportRepository.save(FixtureFactory.createMember(password));
    }
}

