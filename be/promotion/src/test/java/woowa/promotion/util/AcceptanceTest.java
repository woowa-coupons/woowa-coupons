package woowa.promotion.util;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import woowa.promotion.acceptance.SupportRepository;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.fixture.FixtureFactory;
import woowa.promotion.fixture.UserFixture;
import woowa.promotion.global.security.hash.PasswordEncoder;

@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class AcceptanceTest {
    
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Autowired
    protected SupportRepository supportRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    protected Member makeMember(UserFixture userFixture) throws Exception {
        String password = passwordEncoder.encrypt(userFixture.getPassword());
        return supportRepository.save(FixtureFactory.createMember(userFixture, password));
    }
}

