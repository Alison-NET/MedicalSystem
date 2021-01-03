package com.alisonnet.medicalsystem.employeeportal.validator;


import com.alisonnet.medicalsystem.employeeportal.entity.employee.Employee;
import com.alisonnet.medicalsystem.employeeportal.service.employee.CredentialsService;
import com.alisonnet.medicalsystem.employeeportal.service.employee.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class EmployeeCredentialsEmailValidator implements Validator {

    CredentialsService credentialsService;
    BasicEmployeePersonalEmailValidator basicEmployeePersonalEmailValidator;

    @Override
    public boolean supports(Class<?> aClass) {
        return Employee.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;
        basicEmployeePersonalEmailValidator.validate(employee.getBasicInfo(), errors);

        if(!credentialsService.hasUniqueEmail(employee.getCredentials()));
            errors.rejectValue("credentials.email","", "This credentials email is already in use");

    }
}
