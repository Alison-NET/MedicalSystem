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
    public Optional<Employee> getActiveEmployee() {

        Optional<Authentication> maybeAuthentication =
                Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
        if(maybeAuthentication.isEmpty()){
            return Optional.empty();
        }
        Optional<Credentials> maybeCredentials =
                credentialsRepo.findByEmail(maybeAuthentication.get().getName());
        if(maybeCredentials.isEmpty()){
            return Optional.empty();
        }
//        Optional<Employee> maybeEmployee =
//                employeeRepo.findFirstByCredentials(maybeCredentials.get());

        return Optional.ofNullable(maybeCredentials.get().getEmployee());
    }


    @Override
    public List<Employee> getPossibleSupervisorsFor(Employee forEmployee) {
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

        Optional<Employee> beforeSavingMaybeEmployee = findById(employee.getId());
        if(beforeSavingMaybeEmployee.isPresent()){
            Employee beforeSavingEmployee = beforeSavingMaybeEmployee.get();
            //IF DEPARTMENT CHANGED
            if(!beforeSavingEmployee.getJobPosition().getDepartment()
                    .equals(employee.getJobPosition().getDepartment())){

                removeFromDepartmentRelations(beforeSavingEmployee);
                employee.setSupervisor(beforeSavingEmployee.getSupervisor());
                employee.setSubordinates(beforeSavingEmployee.getSubordinates());
            }
        }
    }

    @Override
    public void removeFromDepartmentRelations(Employee employee) {

        if(employee.isDepartmentChief()){
            employee.getSubordinates().forEach(subordinate -> {
                subordinate.setSupervisor(null);
            });
            return;
        }
        if(employee.getSubordinates() != null) {
            employee.getSubordinates().forEach(subordinate -> {
                subordinate.setSupervisor(employee.getSupervisor());
            });
        }
        if(employee.getSupervisor() != null){
            employee.setSupervisor(null);
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
