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

            UnregisteredAccount sourceAcc = (UnregisteredAccount) source;

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

            List<UnregisteredProvider> sourceAccProviders = sourceAcc.getProviders();
            List<Provider> providers = sourceAccProviders.stream()
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

            List<UnregisteredSpecimenPickUpDayTime> sourceAccSpecimenPickUpDayTimes =
                    sourceAcc.getSpecimenPickUpDayTimes();
            List<SpecimenPickUpDayTime> specimenPickUpDayTimes = sourceAccSpecimenPickUpDayTimes.stream()
                    .map(sourceSpecimenPickUpDayTime -> {
                        SpecimenPickUpDayTime specimenPickUpDayTime = new SpecimenPickUpDayTime();
                        specimenPickUpDayTime.setId(sourceSpecimenPickUpDayTime.getId());  // ??
                        specimenPickUpDayTime.setPickUpDayOfWeek(sourceSpecimenPickUpDayTime.getPickUpDayOfWeek());
                        specimenPickUpDayTime.setAccount(convertedAcc);

                        List<PickUpTime> pickUpTimes = sourceSpecimenPickUpDayTime.getPickUpTimes().stream().map(sourcePickUpTime -> {
                            PickUpTime pickUpTime = new PickUpTime();
                            pickUpTime.setId(sourcePickUpTime.getId()); // ??
                            pickUpTime.setPickUpTime(sourcePickUpTime.getPickUpTime());
                            pickUpTime.setSpecimenPickUpDayTime(specimenPickUpDayTime);
                            return pickUpTime;
                        }).collect(Collectors.toList());

                        specimenPickUpDayTime.setPickUpTimes(pickUpTimes);
                        return specimenPickUpDayTime;
                    }).collect(Collectors.toList());

            convertedAcc.setSpecimenPickUpDayTimes(specimenPickUpDayTimes);

            return convertedAcc;
        } else {

            UpdatedAccount sourceAcc = (UpdatedAccount) source;


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

            List<UpdatedProvider> sourceAccProviders = sourceAcc.getProviders();
            List<Provider> providers = sourceAccProviders.stream()
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

            List<UpdatedSpecimenPickUpDayTime> sourceAccSpecimenPickUpDayTimes =
                    sourceAcc.getSpecimenPickUpDayTimes();
            List<SpecimenPickUpDayTime> specimenPickUpDayTimes = sourceAccSpecimenPickUpDayTimes.stream()
                    .map(sourceSpecimenPickUpDayTime -> {
                        SpecimenPickUpDayTime specimenPickUpDayTime = new SpecimenPickUpDayTime();
                        specimenPickUpDayTime.setId(sourceSpecimenPickUpDayTime.getId());  // ??
                        specimenPickUpDayTime.setPickUpDayOfWeek(sourceSpecimenPickUpDayTime.getPickUpDayOfWeek());
                        specimenPickUpDayTime.setAccount(convertedAcc);

                        List<PickUpTime> pickUpTimes = sourceSpecimenPickUpDayTime.getPickUpTimes().stream().map(sourcePickUpTime -> {
                            PickUpTime pickUpTime = new PickUpTime();
                            pickUpTime.setId(sourcePickUpTime.getId()); // ??
                            pickUpTime.setPickUpTime(sourcePickUpTime.getPickUpTime());
                            pickUpTime.setSpecimenPickUpDayTime(specimenPickUpDayTime);
                            return pickUpTime;
                        }).collect(Collectors.toList());

                        specimenPickUpDayTime.setPickUpTimes(pickUpTimes);
                        return specimenPickUpDayTime;
                    }).collect(Collectors.toList());

            convertedAcc.setSpecimenPickUpDayTimes(specimenPickUpDayTimes);

//            Account convertedAcc = new Account();
//            convertedAcc.setId(sourceAcc.getBaseVersion().getId()); // ??
//            convertedAcc.setName(sourceAcc.getName());
//            convertedAcc.setAddressFirst(sourceAcc.getAddressFirst());
//            convertedAcc.setAddressSecond(sourceAcc.getAddressSecond());
//            convertedAcc.setCity(sourceAcc.getCity());
//            convertedAcc.setState(sourceAcc.getState());
//            convertedAcc.setZIP(sourceAcc.getZIP());
//            convertedAcc.setPhone(sourceAcc.getPhone());
//            convertedAcc.setFax(sourceAcc.getFax());
//            convertedAcc.setDirectLine(sourceAcc.getDirectLine());
//            convertedAcc.setContactFirstName(sourceAcc.getContactFirstName());
//            convertedAcc.setContactLastName(sourceAcc.getContactLastName());
//            convertedAcc.setContactEmail(sourceAcc.getContactEmail());
//            convertedAcc.setProviderPortal(sourceAcc.getProviderPortal());
//            convertedAcc.setPaperRequisitions(sourceAcc.getPaperRequisitions());
//
//            List<UpdatedProvider> sourceAccProviders = sourceAcc.getProviders();
//
//            List<Provider> providers = new ArrayList<>();
//            for(int i=0;i<sourceAccProviders.size();i++){
//                Provider provider = new Provider();
//                provider.setId(sourceAcc.getBaseVersion().getProviders().get(i).getId()); // ??
//                provider.setTitle(sourceAccProviders.get(i).getTitle());
//                provider.setFirstName(sourceAccProviders.get(i).getFirstName());
//                provider.setLastName(sourceAccProviders.get(i).getLastName());
//                provider.setEmail(sourceAccProviders.get(i).getEmail());
//                provider.setNPI(sourceAccProviders.get(i).getNPI());
//                provider.setAccount(convertedAcc);
//
//                providers.add(provider);
//            }
//
//            convertedAcc.setProviders(providers);
//
//            List<UpdatedSpecimenPickUpDayTime> sourceAccSpecimenPickUpDayTimes =
//                    sourceAcc.getSpecimenPickUpDayTimes();
//            List<SpecimenPickUpDayTime> specimenPickUpDayTimes = new ArrayList<>();
//            for(int i=0;i<sourceAccSpecimenPickUpDayTimes.size();i++){
//                SpecimenPickUpDayTime specimenPickUpDayTime = new SpecimenPickUpDayTime();
//                specimenPickUpDayTime.setId(sourceAcc.getBaseVersion().getSpecimenPickUpDayTimes().get(i).getId());  // ??
//                specimenPickUpDayTime.setPickUpDayOfWeek(sourceAccSpecimenPickUpDayTimes.get(i).getPickUpDayOfWeek());
//                specimenPickUpDayTime.setAccount(convertedAcc);
//
//                List<PickUpTime> pickUpTimes=new ArrayList<>();
//                for(int j = 0; j < sourceAccSpecimenPickUpDayTimes.get(i).getPickUpTimes().size();j++){
//                    PickUpTime pickUpTime = new PickUpTime();
//                    pickUpTime.setId(sourceAcc.getBaseVersion()
//                            .getSpecimenPickUpDayTimes().get(i).getPickUpTimes().get(j).getId()); // ??
//                    pickUpTime.setPickUpTime(sourceAccSpecimenPickUpDayTimes.get(i).getPickUpTimes().get(j).getPickUpTime());
//                    pickUpTime.setSpecimenPickUpDayTime(specimenPickUpDayTime);
//                    pickUpTimes.add(pickUpTime);
//                }
//
//                specimenPickUpDayTime.setPickUpTimes(pickUpTimes);
//                specimenPickUpDayTimes.add(specimenPickUpDayTime);
//            }
//
//            convertedAcc.setSpecimenPickUpDayTimes(specimenPickUpDayTimes);

            return convertedAcc;
        }
    }
}
