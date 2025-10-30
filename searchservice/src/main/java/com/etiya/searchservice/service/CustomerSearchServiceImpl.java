package com.etiya.searchservice.service;

import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.ContactMedium;
import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.repository.CustomerSearchRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    }

    @Override
    public void updateAddress(Address address) {
        // Find the customer by ID
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(address.getCustomerId());

        if (customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();
            List<Address> addresses = customer.getAddresses();
            boolean existsAddress = addresses.stream().anyMatch(a -> a.getId() == address.getId());
            if(existsAddress){
                List<Address> updatedList = addresses.stream().map(a -> {
                    if(a.getId() == address.getId()){
                        a.setStreet(address.getStreet());
                        a.setHouseNumber(address.getHouseNumber());
                        a.setDescription(address.getDescription());
                        a.setDefault(address.isDefault());
                        a.setDistrictId(address.getDistrictId());
                    }
                    return a;

                }).collect(Collectors.toList());
                customer.setAddresses(updatedList);

            }

            customerSearchRepository.save(customer);
        } else {
            throw new RuntimeException("Customer not found");
        }

    }

    @Override
    public void deleteAddress(Address address) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(address.getCustomerId());

        if (customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();

            // Find the existing address by its ID or some unique identifier (e.g., houseNumber, street)
            boolean addressRemoved = customer.getAddresses().removeIf(addr -> addr.getId() == address.getId());

            if (addressRemoved) {
                // Save the updated customer after address removal
                customerSearchRepository.save(customer);
            } else {
                throw new RuntimeException("Address not found for deletion");
            }
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    @Override
    public void addContactMedium(ContactMedium contactMedium) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(contactMedium.getCustomerId().toString());
        if(customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();
            customer.getContactMediums().add(contactMedium);
            customerSearchRepository.save(customer);
        }
    }

    @Override
    public void updateContactMedium(ContactMedium contactMedium) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(contactMedium.getCustomerId().toString());

        if (customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();
            List<ContactMedium> contactMediums = customer.getContactMediums();
            boolean existsContactMedium = contactMediums.stream().anyMatch(a -> a.getId() == contactMedium.getId());
            if (existsContactMedium) {
                List<ContactMedium> updateList = contactMediums.stream().map(a-> {
                    if(a.getId() == contactMedium.getId()){
                        a.setType(contactMedium.getType());
                        a.setValue(contactMedium.getValue());
                        a.setPrimary(contactMedium.isPrimary());
                    }
                    return a;
                }).collect(Collectors.toList());
                customer.setContactMediums(updateList);
            }

            // Save the updated customer
            customerSearchRepository.save(customer);
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    @Override
    public void deleteContactMedium(ContactMedium contactMedium) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(contactMedium.getCustomerId().toString());

        if (customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();

            // Find the existing address by its ID or some unique identifier (e.g., houseNumber, street)
            boolean contactMediumRemoved = customer.getContactMediums().removeIf(cm -> cm.getId() == contactMedium.getId());

            if (contactMediumRemoved) {
                // Save the updated customer after address removal
                customerSearchRepository.save(customer);
            } else {
                throw new RuntimeException("Contact Medium not found for deletion");
            }
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    @Override
    public List<CustomerSearch> searchAllFields(String keyword) {
        return customerSearchRepository.searchAllFields(keyword);
    }

    @Override
    public List<CustomerSearch> findByFirstNameUsingMatch(String firstName) {
        return customerSearchRepository.findByFirstNameUsingMatch(firstName);
    }

    @Override
    public List<CustomerSearch> findByNationalId(String nationalId) {
        return customerSearchRepository.findByNationalId(nationalId);
    }

    @Override
    public List<CustomerSearch> findByFirstNameUsingFuzzy(String misspelledFirstName) {
        return customerSearchRepository.findByFirstNameUsingFuzzy(misspelledFirstName);
    }

    @Override
    public List<CustomerSearch> searchWithSmartQuery(String userInput) {
        return customerSearchRepository.searchWithSmartQuery(userInput);
    }

    @Override
    public CustomerSearch searchByCustomerId(String customerId) {
        return customerSearchRepository.searchByCustomerId(customerId);
    }

}
