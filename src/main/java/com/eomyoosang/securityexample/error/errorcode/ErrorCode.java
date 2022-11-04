package com.eomyoosang.securityexample.error.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    EXPIRED_SOCIAL_TOKEN(HttpStatus.UNAUTHORIZED, "Expired social token"),
    WRONG_TYPE_SIGNATURE(HttpStatus.UNAUTHORIZED, "Wrong type signature"),
    WRONG_TYPE_TOKEN(HttpStatus.UNAUTHORIZED, "Wrong type token"),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "Expired access token"),
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid access token"),
    ;
    private final HttpStatus httpStatus;
    private final String message;
}