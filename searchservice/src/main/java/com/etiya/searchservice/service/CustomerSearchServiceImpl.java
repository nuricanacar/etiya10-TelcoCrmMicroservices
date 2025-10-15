package com.etiya.searchservice.service;

import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.repository.CustomerSearchRepository;
import org.springframework.stereotype.Service;

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
}
