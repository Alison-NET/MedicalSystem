package com.alisonnet.medicalsystem.employeeportal.service.account.updated.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredProvider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedProvider;
import com.alisonnet.medicalsystem.employeeportal.repository.account.unregistered.UnregisteredProviderRepo;
import com.alisonnet.medicalsystem.employeeportal.repository.account.updated.UpdatedProviderRepo;
import com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.UnregisteredProviderService;
import com.alisonnet.medicalsystem.employeeportal.service.account.updated.UpdatedProviderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UpdatedProviderServiceImpl implements UpdatedProviderService {

    UpdatedProviderRepo updatedProviderRepo;

    @Override
    public List<UpdatedProvider> findAll() {
        return updatedProviderRepo.findAll();
    }

    @Override
    public Optional<UpdatedProvider> findById(int id) {
        return updatedProviderRepo.findById(id);
    }

    @Override
    public UpdatedProvider save(UpdatedProvider updatedProvider) {
        return updatedProviderRepo.save(updatedProvider);
    }

    @Override
    public void remove(UpdatedProvider updatedProvider) {
        updatedProviderRepo.delete(updatedProvider);
    }
}
