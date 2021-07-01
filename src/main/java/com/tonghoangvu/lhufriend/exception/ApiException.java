package com.tonghoangvu.lhufriend.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private final ErrorCode code;

    public ApiException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }
}
