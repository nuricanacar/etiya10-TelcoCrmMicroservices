package com.etiya.searchservice.service;

import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.CustomerSearch;

public interface CustomerSearchService {

    void add(CustomerSearch customerSearch);
    void addAddress(Address address, String customerId);
    void updateAddress(Address address, String customerId);
}
