package woowa.promotion.global.exception.response;

public record ErrorResponse(
        boolean success,
        ErrorCode errorCode
) {
    public static ErrorResponse fail(int status, String message) {
        return new ErrorResponse(false, new ErrorCode(status, message));
    }
}

