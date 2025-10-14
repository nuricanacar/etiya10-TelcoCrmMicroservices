package com.etiya.customerservice.service.rules;

import com.etiya.common.entities.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.localization.LocalizationService;
import com.etiya.customerservice.domain.entities.Customer;
import com.etiya.customerservice.repository.CorporateCustomerRepository;
import com.etiya.customerservice.repository.CustomerRepository;
import com.etiya.customerservice.service.messages.Messages;

public abstract class CustomerBusinessRules<T extends Customer> {

    private final CustomerRepository<T> customerRepository;

    public CustomerBusinessRules(CustomerRepository<T> customerRepository) {
        this.customerRepository = customerRepository;
    }

//    public void checkIfCustomerExists(int id){
//        if (!customerRepository.existsById(id)){
//            throw new BusinessException(localizationService.getMessage(Messages.CustomerExists));
//        }
//    }
}
