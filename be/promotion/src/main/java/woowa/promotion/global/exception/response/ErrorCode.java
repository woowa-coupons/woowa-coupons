package woowa.promotion.global.exception.response;

public record ErrorCode(
        int status,
        String message
) {
}
