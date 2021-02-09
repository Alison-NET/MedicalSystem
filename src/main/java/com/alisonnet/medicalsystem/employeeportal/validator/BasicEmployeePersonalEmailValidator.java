package com.alisonnet.medicalsystem.employeeportal.validator;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
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

        if(!basicEmployeeService.hasUniquePersonalEmail(basicEmployee))
            errors.rejectValue(fieldPath,"", Constants.VALIDATION_MSG_EMAIL_USED);
    }
}
