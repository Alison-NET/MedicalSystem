package com.alisonnet.medicalsystem.employeeportal.exception.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException {

    public UserAlreadyRegisteredException(String message){
        super(message);
    }
}
