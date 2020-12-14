package com.alisonnet.medicalsystem.employeeportal.converter;

import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Account;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.PickUpTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Provider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.SpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredAccount;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredProvider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredSpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedAccount;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

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
                    .map(unregisteredProvider -> {
                        Provider provider = new Provider();
                        provider.setId(unregisteredProvider.getId()); // ??
                        provider.setTitle(unregisteredProvider.getTitle());
                        provider.setFirstName(unregisteredProvider.getFirstName());
                        provider.setLastName(unregisteredProvider.getLastName());
                        provider.setEmail(unregisteredProvider.getEmail());
                        provider.setNPI(unregisteredProvider.getNPI());
                        provider.setAccount(convertedAcc);
                        return provider;
                    }).collect(Collectors.toList());

            convertedAcc.setProviders(providers);

            List<UnregisteredSpecimenPickUpDayTime> sourceAccSpecimenPickUpDayTimes =
                    sourceAcc.getSpecimenPickUpDayTimes();
            List<SpecimenPickUpDayTime> specimenPickUpDayTimes = sourceAccSpecimenPickUpDayTimes.stream()
                    .map(unregisteredSpecimenPickUpDayTime -> {
                        SpecimenPickUpDayTime specimenPickUpDayTime = new SpecimenPickUpDayTime();
                        specimenPickUpDayTime.setId(unregisteredSpecimenPickUpDayTime.getId());  // ??
                        specimenPickUpDayTime.setPickUpDayOfWeek(unregisteredSpecimenPickUpDayTime.getPickUpDayOfWeek());
                        specimenPickUpDayTime.setAccount(convertedAcc);

                        List<PickUpTime> pickUpTimes = unregisteredSpecimenPickUpDayTime.getPickUpTimes().stream().map(unregisteredPickUpTime -> {
                            PickUpTime pickUpTime = new PickUpTime();
                            pickUpTime.setId(unregisteredPickUpTime.getId()); // ??
                            pickUpTime.setPickUpTime(unregisteredPickUpTime.getPickUpTime());
                            pickUpTime.setSpecimenPickUpDayTime(specimenPickUpDayTime);
                            return pickUpTime;
                        }).collect(Collectors.toList());

                        specimenPickUpDayTime.setPickUpTimes(pickUpTimes);
                        return specimenPickUpDayTime;
                    }).collect(Collectors.toList());

            convertedAcc.setSpecimenPickUpDayTimes(specimenPickUpDayTimes);

            return convertedAcc;
        } else {

            return null;
        }
    }
}
