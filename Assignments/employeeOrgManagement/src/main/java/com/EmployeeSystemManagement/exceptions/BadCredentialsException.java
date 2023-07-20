package com.EmployeeSystemManagement.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class BadCredentialsException extends RuntimeException {

    public BadCredentialsException(String message) {

        super(message);
    }


}
