package com.eomyoosang.securityexample.error.exception;

import com.eomyoosang.securityexample.error.errorcode.ErrorCode;
import com.eomyoosang.securityexample.error.errorcode.UserErrorCode;

public class InvalidRefreshTokenException extends RestApiException {
    private final static ErrorCode ERROR_CODE = UserErrorCode.INVALID_REFRESH_TOKEN;

    public InvalidRefreshTokenException() {
        super(ERROR_CODE);
    }
}
