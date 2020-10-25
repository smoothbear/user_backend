package com.dsmpear.main.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
@AllArgsConstructor
public enum ErrorCode {
    // common
    BAD_REQUEST(400, "Bad Request(Invalid Parameter)"),
    UNAUTHORIZED(401,  "UnAuthorized"),

    // auth
    INVALID_TOKEN(401, "Invalid Token");

    private final int status;
    private final String messages;
}
