package woowa.promotion.fixture;

import woowa.promotion.app.member.domain.Member;

public class FixtureFactory {
    public static Member createMember(String password) {
        return new Member("june", "june@codesquad.kr", password);
    }
}
