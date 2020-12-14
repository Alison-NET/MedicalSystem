package com.alisonnet.medicalsystem.config;

import com.alisonnet.medicalsystem.employeeportal.converter.GenericAccountConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new GenericAccountConverter());
    }
}
