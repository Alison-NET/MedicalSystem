package com.alisonnet.medicalsystem.config;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EmployeeUserDetails implements UserDetails {

    private final Employee employee;

    public EmployeeUserDetails(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(employee.getJobPosition().getDepartment().toAuthority()));
        authorities.add(new SimpleGrantedAuthority(employee.getJobPosition().toAuthority()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return employee.getCredentials().getPassword().toLowerCase();
    }

    @Override
    public String getUsername() {
        return employee.getCredentials().getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFirstName() {
        return employee.getBasicInfo().getFirstName();
    }

    public String getLastName() {
        return employee.getBasicInfo().getLastName();
    }
}
