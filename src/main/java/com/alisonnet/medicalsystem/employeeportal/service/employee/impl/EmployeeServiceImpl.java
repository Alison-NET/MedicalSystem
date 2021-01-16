package com.alisonnet.medicalsystem.employeeportal.service.employee.impl;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.Credentials;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.Employee;
import com.alisonnet.medicalsystem.employeeportal.repository.employee.CredentialsRepo;
import com.alisonnet.medicalsystem.employeeportal.repository.employee.EmployeeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.employee.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final CredentialsRepo credentialsRepo;

    @Override
    public Employee save(Employee employee) {
        return employeeRepo.save(employee);
    }
    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Optional<Employee> findById(int id) {
        return employeeRepo.findById(id);
    }

    @Override
    public boolean exists(Employee employee) {
        return employeeRepo.exists(Example.of(employee));
    }

    @Override
    public Optional<Employee> getActiveEmployee() {

        Optional<Authentication> maybeAuthentication =
                Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
        if(maybeAuthentication.isEmpty()){
            return null;
        }
        Optional<Credentials> maybeCredentials =
                credentialsRepo.findByEmail(maybeAuthentication.get().getName());
        if(maybeCredentials.isEmpty()){
            return null;
        }
        Optional<Employee> maybeEmployee =
                employeeRepo.findFirstByCredentials(maybeCredentials.get());

        return maybeEmployee;
    }


    @Override
    public List<Employee> getPossibleSupervisors(Employee forEmployee) {
        List<Employee> employees = employeeRepo.findEmployeesByIdNot(forEmployee.getId());

        return employees.stream().
                filter(employee -> employee.getJobPosition().getDepartment()
                        .equals(forEmployee.getJobPosition().getDepartment()))
                .collect(Collectors.toList());

    }

    @Override
    public void updateRelationsIfNeeded(Employee employee) {

        if(employee.isDepartmentChief())
            employee.setSupervisor(null);

        Optional<Employee> beforeSavingEmployee = findById(employee.getId());
        if(beforeSavingEmployee.isPresent()){

            //IF DEPARTMENT CHANGED
            if(!beforeSavingEmployee.get().getJobPosition().getDepartment()
                    .equals(employee.getJobPosition().getDepartment())){

                beforeSavingEmployee.get().removeFromDepartmentRelations();
                employee.setSupervisor(beforeSavingEmployee.get().getSupervisor());
                employee.setSubordinates(beforeSavingEmployee.get().getSubordinates());
            }
        }
    }


    @Override
    public boolean isInHRDepartment(Employee employee) {
        return employee.getJobPosition().getDepartment().getName().equals(Constants.human_resources_department);
    }

    @Override
    public boolean isInAdminDepartment(Employee employee) {
        return employee.getJobPosition().getDepartment().getName().equals(Constants.system_admin_department);
    }

    @Override
    public boolean canBeEdited(Employee employeeToEdit, Employee activeEmployee) {
        return !((isInHRDepartment(activeEmployee) && isInHRDepartment(employeeToEdit))
                || (isInHRDepartment(activeEmployee) && isInAdminDepartment(employeeToEdit)));
    }
}
