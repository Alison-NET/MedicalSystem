package com.alisonnet.medicalsystem.employeeportal.service.account.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Account;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.PickUpTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Provider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.SpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredPickUpTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredProvider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredSpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.repository.account.AccountRepo;
import com.alisonnet.medicalsystem.employeeportal.service.account.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    AccountRepo accountRepo;

    @Override
    public List<Account> findAll() {
        return accountRepo.findAll();
    }

    @Override
    public void fillUniqueIds(Account account) {

        int accountsAmount = accountRepo.findAll().size();
        int providersAmount = accountRepo.findAll().size();
        int specimenPickUpDayTimeAmount = accountRepo.findAll().size();
        int pickUpTimesAmount = accountRepo.findAll().size();

        account.setId(++accountsAmount);
        for(Provider provider : account.getProviders())
            provider.setId(++providersAmount);

        for(SpecimenPickUpDayTime specimenPickUpDayTime : account.getSpecimenPickUpDayTimes()){
            specimenPickUpDayTime.setId(++specimenPickUpDayTimeAmount);
            for(PickUpTime pickUpTime : specimenPickUpDayTime.getPickUpTimes())
                pickUpTime.setId(++pickUpTimesAmount);
        }

    }

    @Override
    public void fillNeededData(Account account) {
        for(Provider provider : account.getProviders())
            provider.setAccount(account);

        for(SpecimenPickUpDayTime specimenPickUpDayTime : account.getSpecimenPickUpDayTimes()){
            specimenPickUpDayTime.setAccount(account);
            for(PickUpTime pickUpTime : specimenPickUpDayTime.getPickUpTimes())
                pickUpTime.setSpecimenPickUpDayTime(specimenPickUpDayTime);
        }
    }

    @Override
    public Account save(Account account) {
        return accountRepo.save(account);
    }

    @Override
    public Optional<Account> findById(int id) {
        return accountRepo.findById(id);
    }

    @Override
    public void remove(Account account) {
        accountRepo.delete(account);
    }
}
