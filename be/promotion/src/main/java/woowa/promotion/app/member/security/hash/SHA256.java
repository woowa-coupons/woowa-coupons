package woowa.promotion.app.member.security.hash;

import static woowa.promotion.global.exception.ErrorCode.FAILED_ENCRYPTION;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SHA256 implements PasswordEncoder {

    @Override
    public String encrypt(final String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return bytesToHex(md.digest(text.getBytes()));
        } catch (NoSuchAlgorithmException | NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, FAILED_ENCRYPTION.getContent());
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