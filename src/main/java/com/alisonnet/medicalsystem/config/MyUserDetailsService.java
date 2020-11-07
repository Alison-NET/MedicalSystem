package com.alisonnet.medicalsystem.config;

import com.alisonnet.medicalsystem.employeeportal.entity.Credentials;
import com.alisonnet.medicalsystem.employeeportal.entity.Role;
import com.alisonnet.medicalsystem.employeeportal.repository.CredentialsRepo;
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
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    CredentialsRepo credentialsRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Credentials> optCredentials = credentialsRepo.findById(email);

        Credentials credentials =
                optCredentials
                        .orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + email));

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new org.springframework.security.core.userdetails.User(
                credentials.getEmail(),
                credentials.getPassword().toLowerCase(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(credentials.getRoles())
        );
    }

    private static List<GrantedAuthority> getAuthorities (List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
