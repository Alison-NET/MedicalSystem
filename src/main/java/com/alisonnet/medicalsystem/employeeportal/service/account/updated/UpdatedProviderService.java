package com.alisonnet.medicalsystem.employeeportal.service.account.updated;

import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UpdatedProviderService {
    List<UpdatedProvider> findAll();
    Optional<UpdatedProvider> findById(int id);
    UpdatedProvider save(UpdatedProvider updatedProvider);
    void remove(UpdatedProvider updatedProvider);
}
