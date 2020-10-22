package com.dsmpear.main.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
@AllArgsConstructor
public enum ErrorCode {
    // common
    BAD_REQUEST(400, "C400-0" ,"Bad Request(Invalid Parameter)"),
    UNAUTHORIZED(401, "A401-0", "UnAuthorized"),

    // auth
    INVALID_TOKEN(401, "A401-0", "Invalid Token");

    private final int status;
    private final String code;
    private final String messages;
}
