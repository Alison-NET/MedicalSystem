package com.alisonnet.medicalsystem.employeeportal.service.account.updated.impl;

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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdatedAccountServiceImpl implements UpdatedAccountService {

    UpdatedAccountRepo updatedAccountRepo;
    UpdatedProviderRepo updatedProviderRepo;
    UpdatedSpecimenPickUpDayTimeRepo updatedSpecimenPickUpDayTimeRepo;
    UpdatedPickUpTimeRepo updatedPickUpTimeRepo;

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
        int accountsAmount = updatedAccountRepo.findAll().size();
        int providersAmount = updatedProviderRepo.findAll().size();
        int specimenPickUpDayTimeAmount = updatedSpecimenPickUpDayTimeRepo.findAll().size();
        int pickUpTimesAmount = updatedPickUpTimeRepo.findAll().size();

        updatedAccount.setId(++accountsAmount);
        for(UpdatedProvider provider : updatedAccount.getProviders())
            provider.setId(++providersAmount);

        for(UpdatedSpecimenPickUpDayTime specimenPickUpDayTime : updatedAccount.getSpecimenPickUpDayTimes()){
            specimenPickUpDayTime.setId(++specimenPickUpDayTimeAmount);
            for(UpdatedPickUpTime pickUpTime : specimenPickUpDayTime.getPickUpTimes())
                pickUpTime.setId(++pickUpTimesAmount);
        }
    }

    @Override
    public void fillNeededData(UpdatedAccount updatedAccount) {

        for(UpdatedProvider provider : updatedAccount.getProviders())
            provider.setAccount(updatedAccount);

        for(UpdatedSpecimenPickUpDayTime specimenPickUpDayTime : updatedAccount.getSpecimenPickUpDayTimes()){
            specimenPickUpDayTime.setAccount(updatedAccount);
            for(UpdatedPickUpTime pickUpTime : specimenPickUpDayTime.getPickUpTimes())
                pickUpTime.setSpecimenPickUpDayTime(specimenPickUpDayTime);
        }
    }

    @Override
    public void addPickUpTime(UpdatedAccount account, int dayId) {
        UpdatedSpecimenPickUpDayTime specimenPickUpDayTime = account.getSpecimenPickUpDayTimes().get(dayId - 1);
        List<UpdatedPickUpTime> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
        pickUpTimes.add(new UpdatedPickUpTime());
    }

    @Override
    public void removePickUpTime(UpdatedAccount account, int dayId) {
        UpdatedSpecimenPickUpDayTime specimenPickUpDayTime = account.getSpecimenPickUpDayTimes().get(dayId - 1);
        List<UpdatedPickUpTime> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
        pickUpTimes.remove(pickUpTimes.size() - 1);
    }

    @Override
    public void remove(UpdatedAccount updatedAccount) {
        updatedAccountRepo.delete(updatedAccount);
    }
}
