package com.alisonnet.medicalsystem.employeeportal.validator;


import com.alisonnet.medicalsystem.employeeportal.entity.employee.Credentials;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.Employee;
import com.alisonnet.medicalsystem.employeeportal.service.employee.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CredentialsEmailValidator implements Validator {

    @Autowired
    CredentialsService credentialsService;

    String fieldPath;

    public void setFieldPath(String fieldPath) {
        this.fieldPath = fieldPath;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Employee.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Credentials credentials = (Credentials) target;
        if(!credentialsService.hasUniqueEmail(credentials))
            errors.rejectValue(fieldPath,"", "This credentials email is already in use");
    }
}
