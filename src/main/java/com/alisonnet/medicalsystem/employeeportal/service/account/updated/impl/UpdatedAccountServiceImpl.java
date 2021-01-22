package com.alisonnet.medicalsystem.employeeportal.service.account.updated.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.AccountBase;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredAccount;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredPickUpTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredProvider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredSpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedAccount;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedPickUpTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedProvider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedSpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.repository.account.updated.UpdatedAccountRepo;
import com.alisonnet.medicalsystem.employeeportal.repository.account.updated.UpdatedPickUpTimeRepo;
import com.alisonnet.medicalsystem.employeeportal.repository.account.updated.UpdatedProviderRepo;
import com.alisonnet.medicalsystem.employeeportal.repository.account.updated.UpdatedSpecimenPickUpDayTimeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.account.updated.UpdatedAccountService;
import com.alisonnet.medicalsystem.employeeportal.service.account.updated.UpdatedPickUpTimeService;
import com.alisonnet.medicalsystem.employeeportal.service.account.updated.UpdatedProviderService;
import com.alisonnet.medicalsystem.employeeportal.service.account.updated.UpdatedSpecimenPickUpDayTimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdatedAccountServiceImpl implements UpdatedAccountService {

    private final UpdatedAccountRepo updatedAccountRepo;
    private final UpdatedProviderService updatedProviderService;
    private final UpdatedSpecimenPickUpDayTimeService updatedSpecimenPickUpDayTimeService;
    private final UpdatedPickUpTimeService updatedPickUpTimeService;

    @Override
    public List<UpdatedAccount> findAll() {
        return updatedAccountRepo.findAll();
    }

    @Override
    public UpdatedAccount save(UpdatedAccount updatedAccount) {
        return updatedAccountRepo.save(updatedAccount);
    }

    @Override
    public Optional<UpdatedAccount> findById(int id) {
        return updatedAccountRepo.findById(id);
    }

    @Override
    public void fillUniqueIds(UpdatedAccount updatedAccount) {
        int accountUniqueId = getMaxId();
        int providerUniqueId = updatedProviderService.getMaxId();
        int specimenPickUpDayTimeUniqueId = updatedSpecimenPickUpDayTimeService.getMaxId();
        int pickUpTimeUniqueId = updatedPickUpTimeService.getMaxId();

        updatedAccount.setId(++accountUniqueId);
        for(UpdatedProvider provider : updatedAccount.getProviders())
            provider.setId(++providerUniqueId);

        for(UpdatedSpecimenPickUpDayTime specimenPickUpDayTime : updatedAccount.getSpecimenPickUpDayTimes()){
            specimenPickUpDayTime.setId(++specimenPickUpDayTimeUniqueId);
            for(UpdatedPickUpTime pickUpTime : specimenPickUpDayTime.getPickUpTimes())
                pickUpTime.setId(++pickUpTimeUniqueId);
        }
    }

    @Override
    public void fillNeededData(UpdatedAccount updatedAccount) {

        if(updatedAccount.getProviders() != null)
            for(UpdatedProvider provider : updatedAccount.getProviders())
                provider.setAccount(updatedAccount);
        else
            updatedAccount.setProviders(new ArrayList<>());

        for(UpdatedSpecimenPickUpDayTime specimenPickUpDayTime : updatedAccount.getSpecimenPickUpDayTimes()){
            specimenPickUpDayTime.setAccount(updatedAccount);
            if(specimenPickUpDayTime.getPickUpTimes() != null)
                for(UpdatedPickUpTime pickUpTime : specimenPickUpDayTime.getPickUpTimes())
                    pickUpTime.setSpecimenPickUpDayTime(specimenPickUpDayTime);
            else
                specimenPickUpDayTime.setPickUpTimes(new ArrayList<>());
        }
    }

    @Override
    public void remove(UpdatedAccount updatedAccount) {
        updatedAccountRepo.delete(updatedAccount);
    }

    @Override
    public int getMaxId() {
        return updatedAccountRepo.findTopByOrderByIdDesc().map(AccountBase::getId).orElse(0);
    }
}
