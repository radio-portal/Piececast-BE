package org.nhnacademy.piececast.common;

import org.springframework.http.HttpStatus;

public interface ExceptionCode {
    HttpStatus getHttpStatus();
    String getCode();
    String getMessage();
}