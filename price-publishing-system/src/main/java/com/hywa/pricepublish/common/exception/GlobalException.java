package com.hywa.pricepublish.common.exception;

public class GlobalException extends RuntimeException {


    private Short errorCode;

    public GlobalException(String errorMessage) {
        super(errorMessage);
    }

    public GlobalException(Short errCode, String errorMessage) {
        this(errorMessage);
        this.setErrorCode(errCode);
    }


    public Short getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Short errorCode) {
        this.errorCode = errorCode;
    }
}