package task.exception;

import lombok.Getter;

public enum ExceptionCode {

    AGREEMENT_NOT_VALID(400, "'True' is only possible answer to apply for the event.");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}