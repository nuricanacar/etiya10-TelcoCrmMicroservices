package com.etiya.searchservice.service;

import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.repository.CustomerSearchRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerSearchServiceImpl implements CustomerSearchService {
    private final CustomerSearchRepository customerSearchRepository;

    public CustomerSearchServiceImpl(CustomerSearchRepository customerSearchRepository) {
        this.customerSearchRepository = customerSearchRepository;
    }

    @Override
    public void add(CustomerSearch customerSearch) {
        customerSearchRepository.save(customerSearch);
    }

    @Override
    public void addAddress(Address address) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(address.getCustomerId());
        if(customerOpt.isPresent()){
            CustomerSearch customer = customerOpt.get();
            customer.getAddresses().add(address);
            customerSearchRepository.save(customer);
        }
}}
