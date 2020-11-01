package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.BasicEmployee;
import com.alisonnet.medicalsystem.employeeportal.exception.exceptions.BasicEmployeeAlreadyRegisteredException;
import com.alisonnet.medicalsystem.employeeportal.repository.BasicEmployeeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.BasicEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class BasicEmployeeServiceImpl implements BasicEmployeeService {

    BasicEmployeeRepo basicEmployeeRepo;

    @Override
    public BasicEmployee registerNewBasicEmployee(BasicEmployee basicEmployee) {

        if(personalEmailExists(basicEmployee.getPersonalEmail())){
            throw new BasicEmployeeAlreadyRegisteredException("Employee with such an email " + basicEmployee.getPersonalEmail() + " already exists ");
        }
        return basicEmployeeRepo.save(basicEmployee);
    }

    @Override
    public boolean personalEmailExists(String personalEmail) {
        return basicEmployeeRepo.findFirstByPersonalEmail(personalEmail) != null;
    }
}
