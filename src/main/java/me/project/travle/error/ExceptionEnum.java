package me.project.travle.error;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {
    ID_CONFLICT_EXCEPTION(HttpStatus.BAD_REQUEST, "001", "Id already exist"),
    PHONE_CONFLICT_EXCEPTION(HttpStatus.BAD_REQUEST, "002", "phone number already exist"),
    ID_OR_PASSWORD_EXCEPTION(HttpStatus.BAD_REQUEST, "003", "Id or password incorrect exist");
    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
