package com.etiya.customerservice.service.rules;

import com.etiya.common.entities.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.localization.LocalizationService;
import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.repository.AddressRepository;
import com.etiya.customerservice.service.messages.Messages;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressBusinessRules {
    private final AddressRepository addressRepository;
    private final LocalizationService localizationService;

    public AddressBusinessRules(AddressRepository addressRepository, LocalizationService localizationService) {
        this.addressRepository = addressRepository;
        this.localizationService = localizationService;
    }

//    public void checkIfAddressExists(int id) {
//        if(addressRepository.existsById(id)){
//            throw new BusinessException("Address with id " + id + " doesnt exists");
//        }
//    }

    public void checkIsPrimaryOnlyOne(Address address) {
        if (address.isDefault()) {
            UUID ownerCustomerId = address.getCustomer().getId();
            List<Address> addressList = addressRepository.findByCustomerId(ownerCustomerId);
            if (!addressList.isEmpty()) {
                for (Address a : addressList) {
                    a.setDefault(false);
                }
            }
        }
    }

    public void checkIfBillingAccountExists(int id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new BusinessException(localizationService.getMessage(Messages.IdExists)));
        if (!address.billingAccounts.isEmpty()) {
            throw new BusinessException(localizationService.getMessage(Messages.BillingAccountExists));
        }
    }
}