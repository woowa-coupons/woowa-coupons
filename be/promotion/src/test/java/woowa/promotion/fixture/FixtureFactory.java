package woowa.promotion.fixture;

import woowa.promotion.app.member.domain.Member;

public class FixtureFactory {
    public static Member createMember(UserFixture userFixture, String password) {
        return new Member(userFixture.getNickname(), userFixture.getEmail(), password);
    }
}
