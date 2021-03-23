package com.alisonnet.medicalsystem.employeeportal.service.employee.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.Employee;
import com.alisonnet.medicalsystem.employeeportal.service.employee.EmployeeService;
import com.alisonnet.medicalsystem.employeeportal.service.employee.EmployeeTreeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeTreeServiceImpl implements EmployeeTreeService {

    private final EmployeeService employeeService;

    @Override
    public void removeFromDepartmentRelations(Employee employee) {
        if(employeeService.isDepartmentChief(employee)){
            log.info("I was a department chief, my subs: " + employee.getSubordinates());
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
    public void tryUpdateRelations(Employee employee) {
        if(employeeService.isDepartmentChief(employee))
            employee.setSupervisor(null);

        Optional<Employee> beforeSavingMaybeEmployee = employeeService.findById(employee.getId());
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
    public boolean hasInSubordinates(Employee supervisor, Employee subordinate) {
        while(subordinate.getSupervisor() != null){
            if(subordinate.getSupervisor().getId() == supervisor.getId()) return true;
            subordinate = subordinate.getSupervisor();
        }
        return false;
    }
}
