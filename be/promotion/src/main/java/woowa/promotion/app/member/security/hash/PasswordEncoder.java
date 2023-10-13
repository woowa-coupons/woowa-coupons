package woowa.promotion.app.member.security.hash;

public interface PasswordEncoder {
    String encrypt(String text) throws Exception;
}
