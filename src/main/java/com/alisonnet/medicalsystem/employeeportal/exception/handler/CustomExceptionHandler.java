package com.alisonnet.medicalsystem.employeeportal.exception.handler;

import com.alisonnet.medicalsystem.employeeportal.exception.exceptions.BasicEmployeeAlreadyRegisteredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(BasicEmployeeAlreadyRegisteredException.class)
    public String handleBasicEmployeeAlreadyRegisteredException(BasicEmployeeAlreadyRegisteredException ex, Model model){
        log.info(ex.getMessage());
        model.addAttribute("message", ex.getMessage());

        return "action-result";
    }


}
