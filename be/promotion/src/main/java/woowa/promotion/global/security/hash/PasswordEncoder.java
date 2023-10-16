package woowa.promotion.global.security.hash;

import woowa.promotion.global.exception.ApiException;

public interface PasswordEncoder {

    String encrypt(String text) throws ApiException;

}
