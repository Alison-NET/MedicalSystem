package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.BasicEmployee;
import com.alisonnet.medicalsystem.employeeportal.entity.Employee;
import com.alisonnet.medicalsystem.employeeportal.exception.exceptions.BasicEmployeeAlreadyRegisteredException;
import com.alisonnet.medicalsystem.employeeportal.repository.BasicEmployeeRepo;
import com.alisonnet.medicalsystem.employeeportal.repository.EmployeeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.BasicEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        // basic employees info & employees to approve
        List<BasicEmployee> basicEmployees = basicEmployeeRepo.findAll();

        //all approved employees
        List<Employee> employees = employeeRepo.findAll();

        // receive non approved employees
        List<BasicEmployee> unapprovedEmployees =
                basicEmployees.stream()
                        .filter(basicEmployee -> employees.stream()
                                .noneMatch(employee -> employee.getBasicInfo().equals(basicEmployee))
                        ).collect(Collectors.toList());

        return unapprovedEmployees;
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
