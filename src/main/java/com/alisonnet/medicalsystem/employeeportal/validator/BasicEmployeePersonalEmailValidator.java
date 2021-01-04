package com.alisonnet.medicalsystem.employeeportal.validator;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.BasicEmployee;
import com.alisonnet.medicalsystem.employeeportal.service.employee.BasicEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class BasicEmployeePersonalEmailValidator implements Validator {

    @Autowired
    BasicEmployeeService basicEmployeeService;

    String fieldPath;

    public void setFieldPath(String fieldPath) {
        this.fieldPath = fieldPath;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return BasicEmployee.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BasicEmployee basicEmployee = (BasicEmployee) target;

        // If basic employee already present, check if someone else (except me) has such a personal email
        // If not present, just check if such an email exists
//        if(basicEmployeeService.findById(basicEmployee.getId()).isPresent()) {
//            if (!basicEmployeeService.hasUniquePersonalEmail(basicEmployee))
//                errors.rejectValue("basicInfo.personalEmail", "", "This email is already in use");
//        }
//        else if(basicEmployeeService.personalEmailExists(basicEmployee.getPersonalEmail())) {
//            errors.rejectValue("personalEmail", "", "This email is already in use");
//        }



        if(!basicEmployeeService.hasUniquePersonalEmail(basicEmployee))
            errors.rejectValue(fieldPath,"", "This email is already in use");
    }
}
