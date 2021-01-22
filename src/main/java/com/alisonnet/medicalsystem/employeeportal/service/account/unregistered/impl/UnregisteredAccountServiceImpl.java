package com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.AccountBase;
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
import com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.UnregisteredPickUpTimeService;
import com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.UnregisteredProviderService;
import com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.UnregisteredSpecimenPickUpDayTimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UnregisteredAccountServiceImpl implements UnregisteredAccountService {

    private final UnregisteredAccountRepo unregisteredAccountRepo;
    private final UnregisteredProviderService unregisteredProviderService;
    private final UnregisteredSpecimenPickUpDayTimeService unregisteredSpecimenPickUpDayTimeService;
    private final UnregisteredPickUpTimeService unregisteredPickUpTimeService;
    private final PickUpDayOfWeekRepo pickUpDayOfWeekRepo;

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
    public void fillUniqueIds(UnregisteredAccount unregisteredAccount) {

        int accountUniqueId = getMaxId();
        int providerUniqueId = unregisteredProviderService.getMaxId();
        int specimenPickUpDayTimeUniqueId = unregisteredSpecimenPickUpDayTimeService.getMaxId();
        int pickUpTimeUniqueId = unregisteredPickUpTimeService.getMaxId();

        unregisteredAccount.setId(++accountUniqueId);
        for(UnregisteredProvider provider : unregisteredAccount.getProviders())
            provider.setId(++providerUniqueId);

        for(UnregisteredSpecimenPickUpDayTime specimenPickUpDayTime : unregisteredAccount.getSpecimenPickUpDayTimes()){
            specimenPickUpDayTime.setId(++specimenPickUpDayTimeUniqueId);
            for(UnregisteredPickUpTime pickUpTime : specimenPickUpDayTime.getPickUpTimes())
                pickUpTime.setId(++pickUpTimeUniqueId);
        }
    }

    @Override
    public void fillNeededData(UnregisteredAccount unregisteredAccount) {

        if(unregisteredAccount.getProviders() != null)
            for(UnregisteredProvider provider : unregisteredAccount.getProviders())
                provider.setAccount(unregisteredAccount);
        else
            unregisteredAccount.setProviders(new ArrayList<>());


        for(UnregisteredSpecimenPickUpDayTime specimenPickUpDayTime : unregisteredAccount.getSpecimenPickUpDayTimes()){
            specimenPickUpDayTime.setAccount(unregisteredAccount);
            if(specimenPickUpDayTime.getPickUpTimes() != null)
                for(UnregisteredPickUpTime pickUpTime : specimenPickUpDayTime.getPickUpTimes())
                    pickUpTime.setSpecimenPickUpDayTime(specimenPickUpDayTime);
            else
                specimenPickUpDayTime.setPickUpTimes(new ArrayList<>());
        }
    }

    @Override
    public UnregisteredAccount save(UnregisteredAccount unregisteredAccount) {
        return unregisteredAccountRepo.save(unregisteredAccount);
    }

    @Override
    public Optional<UnregisteredAccount> findById(int id) {
        return unregisteredAccountRepo.findById(id);
    }

    @Override
    public void remove(UnregisteredAccount unregisteredAccount) {
        unregisteredAccountRepo.delete(unregisteredAccount);
    }

    @Override
    public int getMaxId() {
        return unregisteredAccountRepo.findTopByOrderByIdDesc().map(AccountBase::getId).orElse(0);
    }
}
