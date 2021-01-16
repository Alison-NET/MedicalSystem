package com.alisonnet.medicalsystem.employeeportal.exception.handler;

import com.alisonnet.medicalsystem.employeeportal.exception.exceptions.AccessDeniedException;
import com.alisonnet.medicalsystem.employeeportal.exception.exceptions.BasicEmployeeAlreadyRegisteredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
