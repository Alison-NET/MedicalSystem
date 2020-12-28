package com.alisonnet.medicalsystem.employeeportal.service.account.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.PickUpDayOfWeek;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Account;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.PickUpTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Provider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.SpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredAccount;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredPickUpTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredProvider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredSpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.repository.account.*;
import com.alisonnet.medicalsystem.employeeportal.service.account.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    AccountRepo accountRepo;
    ProviderRepo providerRepo;
    SpecimenPickUpDayTimeRepo specimenPickUpDayTimeRepo;
    PickUpTimeRepo pickUpTimeRepo;
    PickUpDayOfWeekRepo pickUpDayOfWeekRepo;

    List<PickUpTime> pickUpTimesToDelete = new ArrayList<>();

    @Override
    public List<Account> findAll() {
        return accountRepo.findAll();
    }

    @Override
    public Account save(Account account) {
        pickUpTimesToDelete.forEach(pickUpTime -> pickUpTimeRepo.delete(pickUpTime));
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

        int accountsAmount = accountRepo.findAll().size();
        int providersAmount = providerRepo.findAll().size();
        int specimenPickUpDayTimeAmount = specimenPickUpDayTimeRepo.findAll().size();
        int pickUpTimesAmount = pickUpTimeRepo.findAll().size();

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
    public void fillUniqueEmptyIds(Account account) {

        int accountsAmount = accountRepo.findAll().size();
        int providersAmount = providerRepo.findAll().size();
        int specimenPickUpDayTimeAmount = specimenPickUpDayTimeRepo.findAll().size();
        int pickUpTimesAmount = pickUpTimeRepo.findAll().size();

        if(account.getId()==0)
            account.setId(++accountsAmount);

        for(Provider provider : account.getProviders()){
            if(provider.getId()==0){
                provider.setId(++providersAmount);
            }
        }

        for(SpecimenPickUpDayTime specimenPickUpDayTime : account.getSpecimenPickUpDayTimes()){
            if(specimenPickUpDayTime.getId()==0) {
                specimenPickUpDayTime.setId(++specimenPickUpDayTimeAmount);
            }
            for(PickUpTime pickUpTime : specimenPickUpDayTime.getPickUpTimes()){
                if (pickUpTime.getId()==0){
                    pickUpTime.setId(++pickUpTimesAmount);
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
        int providersAmount = providerRepo.findAll().size();
        int pickUpTimesAmount = pickUpTimeRepo.findAll().size();

        to.setId(from.getId());

        for (int i = 0 ; i < to.getProviders().size() ; i++){
            if(from.getProviders().size() > i) {
                to.getProviders().get(i).setId(from.getProviders().get(i).getId());
            }else{
                to.getProviders().get(i).setId(++providersAmount);
            }
        }

        for (int i = 0; i < to.getSpecimenPickUpDayTimes().size(); i++){
            to.getSpecimenPickUpDayTimes().get(i).setId(from.getSpecimenPickUpDayTimes().get(i).getId());

            for(int j = 0; j < to.getSpecimenPickUpDayTimes().get(i).getPickUpTimes().size(); j++){
                if(from.getSpecimenPickUpDayTimes().get(i).getPickUpTimes().size() > j ) {
                    to.getSpecimenPickUpDayTimes().get(i).getPickUpTimes().get(j)
                            .setId(from.getSpecimenPickUpDayTimes().get(i).getPickUpTimes().get(j).getId());
                }else{
                    to.getSpecimenPickUpDayTimes().get(i).getPickUpTimes().get(j).setId(++pickUpTimesAmount);
                }
            }
        }
    }

    @Override
    public void addPickUpTime(Account account, int dayId) {
        SpecimenPickUpDayTime specimenPickUpDayTime = account.getSpecimenPickUpDayTimes().get(dayId - 1);
        List<PickUpTime> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
        pickUpTimes.add(new PickUpTime());
    }

    @Override
    public void removePickUpTime(Account account, int dayId) {
        SpecimenPickUpDayTime specimenPickUpDayTime = account.getSpecimenPickUpDayTimes().get(dayId - 1);
        List<PickUpTime> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
        pickUpTimesToDelete.add(pickUpTimes.remove(pickUpTimes.size() - 1));
    }

    @Override
    public void remove(Account account) {
        accountRepo.delete(account);
    }
}
