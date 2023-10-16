package woowa.promotion.global.security.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.exception.domain.JwtException;

public class SHA256 implements PasswordEncoder {

    @Override
    public String encrypt(final String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return bytesToHex(md.digest(text.getBytes()));
        } catch (NoSuchAlgorithmException | NullPointerException e) {
            throw new ApiException(JwtException.FAILED_ENCRYPTION);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexBuilder = new StringBuilder();
        for (byte b : bytes) {
            hexBuilder.append(String.format("%02x", b));
        }
        return hexBuilder.toString();
    }

}
