package com.alisonnet.medicalsystem.employeeportal.converter;

import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Account;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.PickUpTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Provider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.SpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredAccount;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredProvider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredSpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedAccount;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedProvider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedSpecimenPickUpDayTime;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenericAccountConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Stream.of(
                new ConvertiblePair(UnregisteredAccount.class, Account.class),
                new ConvertiblePair(UpdatedAccount.class, Account.class))
                .collect(Collectors.toSet());
    }


    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (sourceType.getType() == Account.class) {
            return source;
        }

        if(sourceType.getType() == UnregisteredAccount.class) {
            return convertToAccount((UnregisteredAccount) source);
        } else {
            return convertToAccount((UpdatedAccount) source);
        }
    }

    private Account convertToAccount(UnregisteredAccount sourceAcc) {
        Account convertedAcc = new Account();
        convertedAcc.setId(sourceAcc.getId()); // ??
        convertedAcc.setName(sourceAcc.getName());
        convertedAcc.setAddressFirst(sourceAcc.getAddressFirst());
        convertedAcc.setAddressSecond(sourceAcc.getAddressSecond());
        convertedAcc.setCity(sourceAcc.getCity());
        convertedAcc.setState(sourceAcc.getState());
        convertedAcc.setZIP(sourceAcc.getZIP());
        convertedAcc.setPhone(sourceAcc.getPhone());
        convertedAcc.setFax(sourceAcc.getFax());
        convertedAcc.setDirectLine(sourceAcc.getDirectLine());
        convertedAcc.setContactFirstName(sourceAcc.getContactFirstName());
        convertedAcc.setContactLastName(sourceAcc.getContactLastName());
        convertedAcc.setContactEmail(sourceAcc.getContactEmail());
        convertedAcc.setProviderPortal(sourceAcc.getProviderPortal());
        convertedAcc.setPaperRequisitions(sourceAcc.getPaperRequisitions());

        if(sourceAcc.getProviders() != null) {
            List<Provider> providers = sourceAcc.getProviders().stream()
                    .map(sourceProvider -> {
                        Provider provider = new Provider();
                        provider.setId(sourceProvider.getId()); // ??
                        provider.setTitle(sourceProvider.getTitle());
                        provider.setFirstName(sourceProvider.getFirstName());
                        provider.setLastName(sourceProvider.getLastName());
                        provider.setEmail(sourceProvider.getEmail());
                        provider.setNPI(sourceProvider.getNPI());
                        provider.setAccount(convertedAcc);
                        return provider;
                    }).collect(Collectors.toList());
            convertedAcc.setProviders(providers);
        }

        List<SpecimenPickUpDayTime> specimenPickUpDayTimes = sourceAcc.getSpecimenPickUpDayTimes().stream()
                .map(sourceSpecimenPickUpDayTime -> {
                    SpecimenPickUpDayTime specimenPickUpDayTime = new SpecimenPickUpDayTime();
                    specimenPickUpDayTime.setId(sourceSpecimenPickUpDayTime.getId());  // ??
                    specimenPickUpDayTime.setPickUpDayOfWeek(sourceSpecimenPickUpDayTime.getPickUpDayOfWeek());
                    specimenPickUpDayTime.setAccount(convertedAcc);

                    if(sourceSpecimenPickUpDayTime.getPickUpTimes() != null) {
                        List<PickUpTime> pickUpTimes = sourceSpecimenPickUpDayTime.getPickUpTimes().stream().map(sourcePickUpTime -> {
                            PickUpTime pickUpTime = new PickUpTime();
                            pickUpTime.setId(sourcePickUpTime.getId()); // ??
                            pickUpTime.setPickUpTime(sourcePickUpTime.getPickUpTime());
                            pickUpTime.setSpecimenPickUpDayTime(specimenPickUpDayTime);
                            return pickUpTime;
                        }).collect(Collectors.toList());
                        specimenPickUpDayTime.setPickUpTimes(pickUpTimes);
                    }

                    return specimenPickUpDayTime;
                }).collect(Collectors.toList());

        convertedAcc.setSpecimenPickUpDayTimes(specimenPickUpDayTimes);

        return convertedAcc;
    }

    private Account convertToAccount(UpdatedAccount sourceAcc) {
        Account convertedAcc = new Account();
        convertedAcc.setId(sourceAcc.getId()); // ??
        convertedAcc.setName(sourceAcc.getName());
        convertedAcc.setAddressFirst(sourceAcc.getAddressFirst());
        convertedAcc.setAddressSecond(sourceAcc.getAddressSecond());
        convertedAcc.setCity(sourceAcc.getCity());
        convertedAcc.setState(sourceAcc.getState());
        convertedAcc.setZIP(sourceAcc.getZIP());
        convertedAcc.setPhone(sourceAcc.getPhone());
        convertedAcc.setFax(sourceAcc.getFax());
        convertedAcc.setDirectLine(sourceAcc.getDirectLine());
        convertedAcc.setContactFirstName(sourceAcc.getContactFirstName());
        convertedAcc.setContactLastName(sourceAcc.getContactLastName());
        convertedAcc.setContactEmail(sourceAcc.getContactEmail());
        convertedAcc.setProviderPortal(sourceAcc.getProviderPortal());
        convertedAcc.setPaperRequisitions(sourceAcc.getPaperRequisitions());

        if(sourceAcc.getProviders() != null) {
            List<Provider> providers = sourceAcc.getProviders().stream()
                    .map(sourceProvider -> {
                        Provider provider = new Provider();
                        provider.setId(sourceProvider.getId()); // ??
                        provider.setTitle(sourceProvider.getTitle());
                        provider.setFirstName(sourceProvider.getFirstName());
                        provider.setLastName(sourceProvider.getLastName());
                        provider.setEmail(sourceProvider.getEmail());
                        provider.setNPI(sourceProvider.getNPI());
                        provider.setAccount(convertedAcc);
                        return provider;
                    }).collect(Collectors.toList());

            convertedAcc.setProviders(providers);
        }

        List<SpecimenPickUpDayTime> specimenPickUpDayTimes = sourceAcc.getSpecimenPickUpDayTimes().stream()
                .map(sourceSpecimenPickUpDayTime -> {
                    SpecimenPickUpDayTime specimenPickUpDayTime = new SpecimenPickUpDayTime();
                    specimenPickUpDayTime.setId(sourceSpecimenPickUpDayTime.getId());  // ??
                    specimenPickUpDayTime.setPickUpDayOfWeek(sourceSpecimenPickUpDayTime.getPickUpDayOfWeek());
                    specimenPickUpDayTime.setAccount(convertedAcc);

                    if(sourceSpecimenPickUpDayTime.getPickUpTimes() != null) {
                        List<PickUpTime> pickUpTimes = sourceSpecimenPickUpDayTime.getPickUpTimes().stream().map(sourcePickUpTime -> {
                            PickUpTime pickUpTime = new PickUpTime();
                            pickUpTime.setId(sourcePickUpTime.getId()); // ??
                            pickUpTime.setPickUpTime(sourcePickUpTime.getPickUpTime());
                            pickUpTime.setSpecimenPickUpDayTime(specimenPickUpDayTime);
                            return pickUpTime;
                        }).collect(Collectors.toList());
                        specimenPickUpDayTime.setPickUpTimes(pickUpTimes);
                    }

                    return specimenPickUpDayTime;
                }).collect(Collectors.toList());

        convertedAcc.setSpecimenPickUpDayTimes(specimenPickUpDayTimes);

        return convertedAcc;
    }
}
