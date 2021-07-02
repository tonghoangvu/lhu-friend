package com.tonghoangvu.lhufriend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND);

    private final HttpStatus status;
}
