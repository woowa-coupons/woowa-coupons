package woowa.promotion.util.fixture;


public enum MemberFixture {

    유저_June("june", "june@codesquad.kr", "password"),
    유저_Jinny("Jinny", "jinny@codesquad.kr", "password"),
    유저_Bruni("Bruni", "bruni@codesquad.kr", "password");

    private final String nickname;
    private final String email;
    private final String password;

    MemberFixture(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
