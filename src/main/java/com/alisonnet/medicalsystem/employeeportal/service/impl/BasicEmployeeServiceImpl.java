package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.BasicEmployee;
import com.alisonnet.medicalsystem.employeeportal.exception.exceptions.BasicEmployeeAlreadyRegisteredException;
import com.alisonnet.medicalsystem.employeeportal.repository.BasicEmployeeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.BasicEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class BasicEmployeeServiceImpl implements BasicEmployeeService {

    BasicEmployeeRepo basicEmployeeRepo;

    @Override
    public List<BasicEmployee> findAll() {
        return basicEmployeeRepo.findAll();
    }

    @Override
    public Optional<BasicEmployee> findById(int id) {
        return basicEmployeeRepo.findById(id);
    }

    @Override
    public BasicEmployee save(BasicEmployee basicEmployee) {
        return basicEmployeeRepo.save(basicEmployee);
    }

    @Override
    public void deleteById(int id) {
        basicEmployeeRepo.deleteById(id);
    }

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
