package com.alisonnet.medicalsystem.employeeportal.service.account.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.AccountBase;
import com.alisonnet.medicalsystem.employeeportal.entity.account.PickUpDayOfWeek;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Account;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.PickUpTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Provider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.SpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.repository.account.*;
import com.alisonnet.medicalsystem.employeeportal.service.account.AccountService;
import com.alisonnet.medicalsystem.employeeportal.service.account.PickUpTimeService;
import com.alisonnet.medicalsystem.employeeportal.service.account.ProviderService;
import com.alisonnet.medicalsystem.employeeportal.service.account.SpecimenPickUpDayTimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;
    private final ProviderService providerService;
    private final SpecimenPickUpDayTimeService specimenPickUpDayTimeService;
    private final PickUpTimeService pickUpTimeService;
    private final PickUpDayOfWeekRepo pickUpDayOfWeekRepo;

    List<PickUpTime> pickUpTimesToDelete = new ArrayList<>();

    @Override
    public List<Account> findAll() {
        return accountRepo.findAll();
    }

    @Override
    public Account save(Account account) {
        pickUpTimesToDelete.forEach(pickUpTimeService::remove);
        return accountRepo.save(account);
    }

    @Override
    public Optional<Account> findById(int id) {
        return accountRepo.findById(id);
    }


    @Override
    public Account createAccount() {
        Account account = new Account();
        account.setProviders(new ArrayList<>());
        List<SpecimenPickUpDayTime> specimenPickUpDayTimes = new ArrayList<>();
        List<PickUpDayOfWeek> daysOfWeek = pickUpDayOfWeekRepo.findAllByOrderByIdAsc();
        daysOfWeek.forEach(dayOfWeek->{
            SpecimenPickUpDayTime specimenPickUpDayTime = new SpecimenPickUpDayTime();
            specimenPickUpDayTime.setPickUpTimes(new ArrayList<>());
            specimenPickUpDayTime.setPickUpDayOfWeek(dayOfWeek);
            specimenPickUpDayTimes.add(specimenPickUpDayTime);
        });
        account.setSpecimenPickUpDayTimes(specimenPickUpDayTimes);
        return account;
    }

    @Override
    public void fillUniqueIds(Account account) {

        int accountUniqueId = getMaxId();
        int providerUniqueId = providerService.getMaxId();
        int specimenPickUpDayTimeUniqueId = specimenPickUpDayTimeService.getMaxId();
        int pickUpTimeUniqueId = pickUpTimeService.getMaxId();

        account.setId(++accountUniqueId);
        for(Provider provider : account.getProviders())
            provider.setId(++providerUniqueId);

        for(SpecimenPickUpDayTime specimenPickUpDayTime : account.getSpecimenPickUpDayTimes()){
            specimenPickUpDayTime.setId(++specimenPickUpDayTimeUniqueId);
            for(PickUpTime pickUpTime : specimenPickUpDayTime.getPickUpTimes())
                pickUpTime.setId(++pickUpTimeUniqueId);
        }

    }

    @Override
    public void fillUniqueEmptyIds(Account account) {

        int accountUniqueId = getMaxId();
        int providerUniqueId = providerService.getMaxId();
        int specimenPickUpDayTimeUniqueId = specimenPickUpDayTimeService.getMaxId();
        int pickUpTimeUniqueId = pickUpTimeService.getMaxId();

        if(account.getId()==0)
            account.setId(++accountUniqueId);

        for(Provider provider : account.getProviders()){
            if(provider.getId()==0){
                provider.setId(++providerUniqueId);
            }
        }

        for(SpecimenPickUpDayTime specimenPickUpDayTime : account.getSpecimenPickUpDayTimes()){
            if(specimenPickUpDayTime.getId()==0) {
                specimenPickUpDayTime.setId(++specimenPickUpDayTimeUniqueId);
            }
            for(PickUpTime pickUpTime : specimenPickUpDayTime.getPickUpTimes()){
                if (pickUpTime.getId()==0){
                    pickUpTime.setId(++pickUpTimeUniqueId);
                }
            }
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
    public void matchIdsGenerateUniqueIfNeeded(Account from, Account to) {
        int providerUniqueId = providerService.getMaxId();
        int pickUpTimeUniqueId = pickUpTimeService.getMaxId();

        to.setId(from.getId());

        for (int i = 0 ; i < to.getProviders().size() ; i++){
            if(from.getProviders().size() > i) {
                to.getProviders().get(i).setId(from.getProviders().get(i).getId());
            }else{
                to.getProviders().get(i).setId(++providerUniqueId);
            }
        }

        for (int i = 0; i < to.getSpecimenPickUpDayTimes().size(); i++){
            to.getSpecimenPickUpDayTimes().get(i).setId(from.getSpecimenPickUpDayTimes().get(i).getId());

            for(int j = 0; j < to.getSpecimenPickUpDayTimes().get(i).getPickUpTimes().size(); j++){
                if(from.getSpecimenPickUpDayTimes().get(i).getPickUpTimes().size() > j ) {
                    to.getSpecimenPickUpDayTimes().get(i).getPickUpTimes().get(j)
                            .setId(from.getSpecimenPickUpDayTimes().get(i).getPickUpTimes().get(j).getId());
                }else{
                    to.getSpecimenPickUpDayTimes().get(i).getPickUpTimes().get(j).setId(++pickUpTimeUniqueId);
                }
            }
        }
    }

//    @Override
//    public void addPickUpTime(Account account, int dayId) {
//        SpecimenPickUpDayTime specimenPickUpDayTime = account.getSpecimenPickUpDayTimes().get(dayId - 1);
//        List<PickUpTime> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
//        pickUpTimes.add(new PickUpTime());
//    }
//
//    @Override
//    public void removePickUpTime(Account account, int dayId) {
//        SpecimenPickUpDayTime specimenPickUpDayTime = account.getSpecimenPickUpDayTimes().get(dayId - 1);
//        List<PickUpTime> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
//        pickUpTimesToDelete.add(pickUpTimes.remove(pickUpTimes.size() - 1));
//    }

    @Override
    public void remove(Account account) {
        accountRepo.delete(account);
    }

    @Override
    public int getMaxId() {
        return accountRepo.findTopByOrderByIdDesc().map(AccountBase::getId).orElse(0);
    }
}
