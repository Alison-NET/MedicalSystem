package com.alisonnet.medicalsystem.employeeportal.exception.handler;

import com.alisonnet.medicalsystem.employeeportal.exception.exceptions.AccessDeniedException;
import com.alisonnet.medicalsystem.employeeportal.exception.exceptions.BasicEmployeeAlreadyRegisteredException;
import com.alisonnet.medicalsystem.employeeportal.exception.exceptions.InvalidPathVariableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(BasicEmployeeAlreadyRegisteredException.class)
    public String handleBasicEmployeeAlreadyRegisteredException(BasicEmployeeAlreadyRegisteredException ex, Model model){
        model.addAttribute("message", ex.getMessage());
        return "action-result";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex, Model model){
        model.addAttribute("message", ex.getMessage());
        return "action-result";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidPathVariableException.class)
    public String handleInvalidPathVariableException(InvalidPathVariableException ex, Model model){
        model.addAttribute("message", ex.getMessage());
        return "action-result";
    }
}
