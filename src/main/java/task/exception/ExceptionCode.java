package task.exception;

import lombok.Getter;

public enum ExceptionCode {

    AGREEMENT_NOT_VALID(400, "'True' is only possible answer to apply for the event."),
    EMAIL_EXISTS(409, "This email already exists in database."),
    PHONE_EXISTS(409, "This phone number already exists in database.");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}