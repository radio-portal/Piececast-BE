package org.nhnacademy.piececast.common;

public record ExceptionResponse(
        String code,
        String message
) {
    static ExceptionResponse from(ExceptionCode code) {
        return new ExceptionResponse(code.getCode(), code.getMessage());
    }
}