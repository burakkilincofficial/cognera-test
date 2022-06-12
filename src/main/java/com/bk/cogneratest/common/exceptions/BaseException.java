package com.bk.cogneratest.common.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * @author Burak KILINC
 * @since 2022-06-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends Exception {
    private final HttpStatus status;

    public BaseException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
