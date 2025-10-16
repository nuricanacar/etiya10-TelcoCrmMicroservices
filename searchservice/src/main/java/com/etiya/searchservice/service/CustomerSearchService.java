package com.etiya.searchservice.service;

import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.ContactMedium;
import com.etiya.searchservice.domain.CustomerSearch;

import java.util.List;

public interface CustomerSearchService {

    void add(CustomerSearch customerSearch);
    void addAddress(Address address, String customerId);
    void updateAddress(Address address, String customerId);
    void deleteAddress(int addressId, String customerId);
    void addContactMedium(ContactMedium contactMedium, String customerId);
    void updateContactMedium(ContactMedium contactMedium, String customerId);
    void deleteContactMedium(int id, String customerId);
    List<CustomerSearch> searchAllFields(String keyword);

}
