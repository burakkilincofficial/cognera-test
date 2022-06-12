package com.bk.cogneratest.common.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Error Response Object.
 * @since 2021-11-25
 * @author burak kilinc
 */
@Data
@AllArgsConstructor
public class ErrorDetails {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;
    private String message;
    private String details;
}