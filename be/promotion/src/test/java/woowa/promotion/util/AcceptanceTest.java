package woowa.promotion.util;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import woowa.promotion.acceptance.SupportRepository;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.fixture.FixtureFactory;
import woowa.promotion.fixture.UserFixture;
import woowa.promotion.global.security.hash.PasswordEncoder;

@Acceptance
public abstract class AcceptanceTest {

    @Autowired
    protected SupportRepository supportRepository;
    @LocalServerPort
    private int port;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    protected Member makeMember(UserFixture userFixture) {
        String password = passwordEncoder.encrypt(userFixture.getPassword());
        return supportRepository.save(FixtureFactory.createMember(userFixture, password));
    }
}

