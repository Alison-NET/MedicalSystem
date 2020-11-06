package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.Role;
import com.alisonnet.medicalsystem.employeeportal.repository.RoleRepo;
import com.alisonnet.medicalsystem.employeeportal.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    RoleRepo roleRepo;

    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }
}
