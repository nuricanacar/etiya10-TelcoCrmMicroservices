package com.etiya.searchservice.service;

import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.ContactMedium;
import com.etiya.searchservice.domain.CustomerSearch;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerSearchService {

    void add(CustomerSearch customerSearch);
    void addAddress(Address address);
    void updateAddress(Address address);
    void deleteAddress(Address address);
    void addContactMedium(ContactMedium contactMedium);
    void updateContactMedium(ContactMedium contactMedium);
    void deleteContactMedium(ContactMedium contactMedium);
    List<CustomerSearch> searchAllFields(String keyword);
    List<CustomerSearch> findByFirstNameUsingMatch(String firstName);
    List<CustomerSearch> findByNationalId(String nationalId);
    List<CustomerSearch> findByFirstNameUsingFuzzy(String misspelledFirstName);
    List<CustomerSearch> searchWithSmartQuery(String userInput);


}
