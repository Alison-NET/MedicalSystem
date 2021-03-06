package com.alisonnet.medicalsystem.employeeportal.service.employee.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.BasicEmployee;
import com.alisonnet.medicalsystem.employeeportal.exception.exceptions.BasicEmployeeAlreadyRegisteredException;
import com.alisonnet.medicalsystem.employeeportal.repository.employee.BasicEmployeeRepo;
import com.alisonnet.medicalsystem.employeeportal.repository.employee.EmployeeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.employee.BasicEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class BasicEmployeeServiceImpl implements BasicEmployeeService {

    BasicEmployeeRepo basicEmployeeRepo;
    EmployeeRepo employeeRepo;

    @Override
    public List<BasicEmployee> findAll() {
        return basicEmployeeRepo.findAll();
    }

    @Override
    public List<BasicEmployee> getUnapprovedEmployees() {
        List<BasicEmployee> basicEmployees = basicEmployeeRepo.findAll();
        return basicEmployees.stream()
                .filter(basicEmployee -> basicEmployee.getFullInfo()==null).collect(Collectors.toList());
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

//    @Override
//    public BasicEmployee registerNewBasicEmployee(BasicEmployee basicEmployee) {
//
//        if(personalEmailExists(basicEmployee.getPersonalEmail())){
//            throw new BasicEmployeeAlreadyRegisteredException
//                    ("Employee with such an email " + basicEmployee.getPersonalEmail() + " already exists ");
//        }
//        return basicEmployeeRepo.save(basicEmployee);
//    }


    @Override
    public boolean hasUniquePersonalEmail(BasicEmployee basicEmployee) {
        return basicEmployeeRepo.findAll().stream()
                .filter(basEmp -> basEmp.getId() != basicEmployee.getId())
                .noneMatch( basEmp -> basEmp.getPersonalEmail().toUpperCase()
                        .equals(basicEmployee.getPersonalEmail().toUpperCase()));
    }

    @Override
    public boolean personalEmailExists(String personalEmail) {
        return basicEmployeeRepo.findFirstByPersonalEmail(personalEmail) != null;
    }

}
