package com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.PickUpDayOfWeek;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredAccount;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredPickUpTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredProvider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredSpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.repository.account.PickUpDayOfWeekRepo;
import com.alisonnet.medicalsystem.employeeportal.repository.account.unregistered.UnregisteredAccountRepo;
import com.alisonnet.medicalsystem.employeeportal.repository.account.unregistered.UnregisteredPickUpTimeRepo;
import com.alisonnet.medicalsystem.employeeportal.repository.account.unregistered.UnregisteredProviderRepo;
import com.alisonnet.medicalsystem.employeeportal.repository.account.unregistered.UnregisteredSpecimenPickUpDayTimeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.UnregisteredAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UnregisteredAccountServiceImpl implements UnregisteredAccountService {

    UnregisteredAccountRepo unregisteredAccountRepo;
    UnregisteredProviderRepo unregisteredProviderRepo;
    UnregisteredSpecimenPickUpDayTimeRepo unregisteredSpecimenPickUpDayTimeRepo;
    UnregisteredPickUpTimeRepo unregisteredPickUpTimeRepo;
    PickUpDayOfWeekRepo pickUpDayOfWeekRepo;

    @Override
    public UnregisteredAccount createUnregisteredAccount() {
        UnregisteredAccount account = new UnregisteredAccount();
        account.setProviders(new ArrayList<>());
        List<UnregisteredSpecimenPickUpDayTime> specimenPickUpDayTimes = new ArrayList<>();
        List<PickUpDayOfWeek> daysOfWeek = pickUpDayOfWeekRepo.findAllByOrderByIdAsc();
        daysOfWeek.forEach(dayOfWeek->{
            UnregisteredSpecimenPickUpDayTime specimenPickUpDayTime = new UnregisteredSpecimenPickUpDayTime();
            specimenPickUpDayTime.setPickUpTimes(new ArrayList<>());
            specimenPickUpDayTime.setPickUpDayOfWeek(dayOfWeek);
            specimenPickUpDayTimes.add(specimenPickUpDayTime);
        });
        account.setSpecimenPickUpDayTimes(specimenPickUpDayTimes);
        return account;
    }

    @Override
    public List<UnregisteredAccount> findAll() {
        return unregisteredAccountRepo.findAll();
    }

    @Override
    public UnregisteredAccount save(UnregisteredAccount unregisteredAccount) {

        int accountsAmount = unregisteredAccountRepo.findAll().size();
        int providersAmount = unregisteredProviderRepo.findAll().size();
        int specimenPickUpDayTimeAmount = unregisteredSpecimenPickUpDayTimeRepo.findAll().size();
        int pickUpTimesAmount = unregisteredPickUpTimeRepo.findAll().size();

        unregisteredAccount.setId(++accountsAmount);
        for(UnregisteredProvider provider : unregisteredAccount.getProviders()) {
            provider.setId(++providersAmount);
            provider.setAccount(unregisteredAccount);
        }

        for(UnregisteredSpecimenPickUpDayTime specimenPickUpDayTime : unregisteredAccount.getSpecimenPickUpDayTimes()){
            specimenPickUpDayTime.setId(++specimenPickUpDayTimeAmount);
            specimenPickUpDayTime.setAccount(unregisteredAccount);
            for(UnregisteredPickUpTime pickUpTime : specimenPickUpDayTime.getPickUpTimes()){
                pickUpTime.setId(++pickUpTimesAmount);
                pickUpTime.setSpecimenPickUpDayTime(specimenPickUpDayTime);
            }
        }

        return unregisteredAccountRepo.save(unregisteredAccount);
    }

    @Override
    public Optional<UnregisteredAccount> findById(int id) {
        return unregisteredAccountRepo.findById(id);
    }

    @Override
    public void addPickUpTime(UnregisteredAccount account, int dayId) {
        UnregisteredSpecimenPickUpDayTime specimenPickUpDayTime = account.getSpecimenPickUpDayTimes().get(dayId - 1);
        List<UnregisteredPickUpTime> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
        pickUpTimes.add(new UnregisteredPickUpTime());
    }

    @Override
    public void removePickUpTime(UnregisteredAccount account, int dayId) {
        UnregisteredSpecimenPickUpDayTime specimenPickUpDayTime = account.getSpecimenPickUpDayTimes().get(dayId - 1);
        List<UnregisteredPickUpTime> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
        pickUpTimes.remove(pickUpTimes.size() - 1);
    }

    @Override
    public void remove(UnregisteredAccount unregisteredAccount) {
        unregisteredAccountRepo.delete(unregisteredAccount);
    }
}
