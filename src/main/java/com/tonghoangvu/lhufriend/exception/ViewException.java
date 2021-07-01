package com.tonghoangvu.lhufriend.exception;

import lombok.Getter;

@Getter
public class ViewException extends RuntimeException {
    private final ErrorCode code;

    public ViewException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }
}
