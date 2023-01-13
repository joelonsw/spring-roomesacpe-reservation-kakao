package roomescape.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    RESERVATION_NOT_FOUND("예약이 존재하지 않습니다", HttpStatus.NOT_FOUND),
    TIME_TABLE_NOT_AVAILABLE("예약할 수 없는 시간입니다", HttpStatus.BAD_REQUEST),
    DUPLICATED_RESERVATION("중복된 예약이 존재합니다", HttpStatus.CONFLICT),
    VALID_INPUT_REQUIRED("알맞은 양식으로 요청해주세요", HttpStatus.BAD_REQUEST),
    THEME_NOT_FOUND("테마가 존재하지 않습니다", HttpStatus.NOT_FOUND),
    THEME_DEPENDENT_ON_RESERVATION("해당 테마에 잡힌 예약이 있습니다.", HttpStatus.BAD_REQUEST),
    DUPLICATED_THEME_NAME("테마의 이름이 중복됩니다", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}