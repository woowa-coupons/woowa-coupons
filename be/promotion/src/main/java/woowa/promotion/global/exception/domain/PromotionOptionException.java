package woowa.promotion.global.exception.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import woowa.promotion.global.exception.CustomException;

@Getter
@RequiredArgsConstructor
public enum PromotionOptionException implements CustomException {
    
    NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 프로모션 옵션 입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
