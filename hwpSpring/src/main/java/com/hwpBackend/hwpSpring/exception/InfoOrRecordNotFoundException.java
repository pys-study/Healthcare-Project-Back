package com.hwpBackend.hwpSpring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InfoOrRecordNotFoundException extends RuntimeException {
    public InfoOrRecordNotFoundException(String message) {
        super(message);
    }
}
