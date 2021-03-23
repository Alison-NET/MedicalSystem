package com.alisonnet.medicalsystem.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    EmployeeUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
//                .antMatchers("/employee-portal/**").authenticated()
                .antMatchers("/employee-portal/admin/**").hasAuthority("SYSTEM_ADMIN_DEP")
                .antMatchers("/employee-portal/hr/**").hasAnyAuthority("HUMAN_RESOURCES_DEP", "SYSTEM_ADMIN_DEP")
                .antMatchers("/employee-portal/sales/**").hasAuthority( "SALES_DEP")
                .antMatchers("/employee-portal/employee/**").authenticated()
                .antMatchers("/employee-portal/document/**").authenticated()
                .antMatchers("/", "/employee-portal/new-employee").permitAll()
                .and()
                .formLogin()
                .loginPage("/employee-portal/login")
                .defaultSuccessUrl("/employee-portal")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/employee-portal/login");

    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
