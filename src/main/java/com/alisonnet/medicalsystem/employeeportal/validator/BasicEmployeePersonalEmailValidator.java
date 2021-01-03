package com.alisonnet.medicalsystem.employeeportal.validator;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.BasicEmployee;
import com.alisonnet.medicalsystem.employeeportal.service.employee.BasicEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
@AllArgsConstructor
public class BasicEmployeePersonalEmailValidator implements Validator {

    BasicEmployeeService basicEmployeeService;

    @Override
    public boolean supports(Class<?> aClass) {
        return BasicEmployee.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BasicEmployee basicEmployee = (BasicEmployee) target;
//
//        if(basicEmployeeService.findById(basicEmployee.getId()).isPresent())
//            if(basicEmployeeService.hasUniquePersonalEmail(basicEmployee))
//               return;
//        else if(!basicEmployeeService.personalEmailExists(basicEmployee.getPersonalEmail()))
//            return;


        if(!basicEmployeeService.hasUniquePersonalEmail(basicEmployee))
            errors.rejectValue("personalEmail","", "This email is already in use");
    }
}
