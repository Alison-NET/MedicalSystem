package com.alisonnet.medicalsystem.employeeportal.exception.exceptions;

public class AccessDeniedException extends RuntimeException{

    public AccessDeniedException(String message){
        super(message);
    }
}
