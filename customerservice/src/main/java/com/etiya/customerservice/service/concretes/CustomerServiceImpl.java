package com.etiya.customerservice.service.concretes;

import com.etiya.common.entities.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.customerservice.repository.CorporateCustomerRepository;
import com.etiya.customerservice.repository.IndividualCustomerRepository;
import com.etiya.customerservice.service.abstracts.CustomerService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final IndividualCustomerRepository individualCustomerRepository;
    private final CorporateCustomerRepository corporateCustomerRepository;

    public CustomerServiceImpl(IndividualCustomerRepository individualCustomerRepository, CorporateCustomerRepository corporateCustomerRepository) {
        this.individualCustomerRepository = individualCustomerRepository;
        this.corporateCustomerRepository = corporateCustomerRepository;
    }

    @Override
    public boolean existsById(UUID id) {
        return false;
    }

    @Override
    public String getCustomerType(UUID customerId) {
        // Individual Customer mı?
        if (individualCustomerRepository.existsById(customerId)) {
            return "INDIVIDUAL";
        }

        if (corporateCustomerRepository.existsById(customerId)) {
            return "CORPORATE";
        }

        throw new BusinessException("Customer not found with id: " + customerId);
    }
}
