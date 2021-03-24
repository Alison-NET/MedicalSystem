package com.alisonnet.medicalsystem.config;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.Credentials;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.JobPosition;
import com.alisonnet.medicalsystem.employeeportal.repository.employee.CredentialsRepo;
import com.alisonnet.medicalsystem.employeeportal.service.employee.CredentialsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeUserDetailsService implements UserDetailsService {

    private final CredentialsService credentialsService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Credentials credentials = credentialsService.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + email));
        return new EmployeeUserDetails(credentials.getEmployee());
    }
}
