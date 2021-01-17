package com.alisonnet.medicalsystem.employeeportal.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class InvalidPathVariableException extends  RuntimeException {
    public InvalidPathVariableException(String message){
        super(message);
    }
}
