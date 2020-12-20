package com.alisonnet.medicalsystem.employeeportal.converter;

import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Account;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.PickUpTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Provider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.SpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredAccount;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedAccount;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedPickUpTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedProvider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedSpecimenPickUpDayTime;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenericUpdatedAccountConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Stream.of(new ConvertiblePair(Account.class, UpdatedAccount.class)).collect(Collectors.toSet());
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {

        if(sourceType.getType() == UnregisteredAccount.class){
            return source;
        }

        if(sourceType.getType() == Account.class){
            Account sourceAcc = (Account) source;
            
            UpdatedAccount convertedAcc = new UpdatedAccount();
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

            List<Provider> sourceAccProviders = sourceAcc.getProviders();

            List<UpdatedProvider> providers = sourceAccProviders.stream()
                    .map(sourceProvider -> {
                        UpdatedProvider provider = new UpdatedProvider();
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

            List<SpecimenPickUpDayTime> sourceAccSpecimenPickUpDayTimes = sourceAcc.getSpecimenPickUpDayTimes();

            List<UpdatedSpecimenPickUpDayTime> specimenPickUpDayTimes = sourceAccSpecimenPickUpDayTimes.stream()
                    .map(sourceSpecimenPickUpDayTime -> {
                        UpdatedSpecimenPickUpDayTime specimenPickUpDayTime = new UpdatedSpecimenPickUpDayTime();
                        specimenPickUpDayTime.setId(sourceSpecimenPickUpDayTime.getId());  // ??
                        specimenPickUpDayTime.setPickUpDayOfWeek(sourceSpecimenPickUpDayTime.getPickUpDayOfWeek());
                        specimenPickUpDayTime.setAccount(convertedAcc);

                        List<UpdatedPickUpTime> pickUpTimes = sourceSpecimenPickUpDayTime.getPickUpTimes().stream().map(sourcePickUpTime -> {
                            UpdatedPickUpTime pickUpTime = new UpdatedPickUpTime();
                            pickUpTime.setId(sourcePickUpTime.getId()); // ??
                            pickUpTime.setPickUpTime(sourcePickUpTime.getPickUpTime());
                            pickUpTime.setSpecimenPickUpDayTime(specimenPickUpDayTime);
                            return pickUpTime;
                        }).collect(Collectors.toList());

                        specimenPickUpDayTime.setPickUpTimes(pickUpTimes);
                        return specimenPickUpDayTime;
                    }).collect(Collectors.toList());

            convertedAcc.setSpecimenPickUpDayTimes(specimenPickUpDayTimes);
            convertedAcc.setBaseVersion(sourceAcc);
            return convertedAcc;
        }

        return null;
    }
}
