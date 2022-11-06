package com.eomyoosang.securityexample.error.exception;

import com.eomyoosang.securityexample.error.errorcode.ErrorCode;
import com.eomyoosang.securityexample.error.errorcode.UserErrorCode;

public class ExpiredRefreshTokenException extends RestApiException {
    private final static ErrorCode ERROR_CODE = UserErrorCode.EXPIRED_REFRESH_TOKEN;

    public ExpiredRefreshTokenException() {
        super(ERROR_CODE);
    }

}
