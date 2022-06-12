package com.bk.cogneratest.common.exceptions;

import org.springframework.http.HttpStatus;

/**
 * TODO Describe this class.
 * @since 2022-06-12
 * @author burak kilinc
 */
public class DocumentFileNotFoundException extends BaseException {
    public DocumentFileNotFoundException(String fileName) {
        super(HttpStatus.BAD_REQUEST, String.format("File '%s' not found", fileName));
    }
}

