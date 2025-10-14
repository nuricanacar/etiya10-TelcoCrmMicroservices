package com.etiya.customerservice.service.concretes;

import com.etiya.customerservice.domain.entities.ContactMedium;
import com.etiya.customerservice.repository.ContactMediumRepository;
import com.etiya.customerservice.service.abstracts.ContactMediumService;
import com.etiya.customerservice.service.abstracts.CustomerService;
import com.etiya.customerservice.service.mappings.ContactMediumMapper;
import com.etiya.customerservice.service.requests.contactMedium.CreateContactMediumRequest;
import com.etiya.customerservice.service.requests.contactMedium.UpdateContactMediumRequest;
import com.etiya.customerservice.service.responses.contactMedium.CreatedContactMediumResponse;
import com.etiya.customerservice.service.responses.contactMedium.GetContactMediumResponse;
import com.etiya.customerservice.service.responses.contactMedium.GetListContactMediumResponse;
import com.etiya.customerservice.service.responses.contactMedium.UpdatedContactMediumResponse;
import com.etiya.customerservice.service.rules.ContactMediumBusinessRules;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContactMediumServiceImpl implements ContactMediumService {

    private final ContactMediumRepository contactMediumRepository;
    private final ContactMediumBusinessRules contactMediumBusinessRules;
    private final CustomerService customerService;

    public ContactMediumServiceImpl(ContactMediumRepository contactMediumRepository, ContactMediumBusinessRules contactMediumBusinessRules, CustomerService customerService) {
        this.contactMediumRepository = contactMediumRepository;
        this.contactMediumBusinessRules = contactMediumBusinessRules;
        this.customerService = customerService;
    }

    @Override
    public CreatedContactMediumResponse add(CreateContactMediumRequest request) {
        ContactMedium contactMedium = ContactMediumMapper.INSTANCE.getContactMediumFromCreateContactMediumRequest(request);
        contactMediumBusinessRules.checkIsPrimaryOnlyOne(contactMedium);
        customerService.existsById(request.getCustomerId());
        ContactMedium created =  contactMediumRepository.save(contactMedium);
        CreatedContactMediumResponse response = ContactMediumMapper.INSTANCE.getCreatedContactMediumResponseFromContactMedium(created);
        return response;
    }

    @Override
    public UpdatedContactMediumResponse update(UpdateContactMediumRequest request) {
        ContactMedium contactMediumOld = contactMediumRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Contact Medium not found"));

        ContactMedium contactMedium = ContactMediumMapper.INSTANCE.contactMediumFromUpdateContactMediumRequest(request, contactMediumOld);
        ContactMedium updated = contactMediumRepository.save(contactMedium);

        UpdatedContactMediumResponse response = ContactMediumMapper.INSTANCE.getUpdatedContactMediumResponseFromContactMedium(updated);

        return response;
    }

    @Override
    public List<GetListContactMediumResponse> getList() {
        List<ContactMedium> contactMediums = contactMediumRepository.findAll();

        List<GetListContactMediumResponse> responses = ContactMediumMapper.INSTANCE.getListContactMediumResponsesFromContactMedium(contactMediums);

        return responses;
    }

    @Override
    public void delete(int id) {
        ContactMedium contactMedium = contactMediumRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        contactMediumBusinessRules.checkIsPrimary(contactMedium);
        contactMediumRepository.delete(contactMedium);
    }

    @Override
    public void softDelete(int id) {
        ContactMedium contactMedium = contactMediumRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        contactMediumBusinessRules.checkIsPrimary(contactMedium);
        contactMedium.setDeletedDate(LocalDateTime.now());
        contactMediumRepository.save(contactMedium);
    }

    @Override
    public GetContactMediumResponse getById(int id) {
        ContactMedium contactMedium = contactMediumRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        GetContactMediumResponse response = ContactMediumMapper.INSTANCE.getContactMediumResponseFromContactMedium(contactMedium);
        return response;
    }

    @Override
    public GetContactMediumResponse getByValue(String value) {
        ContactMedium contactMedium =
                contactMediumRepository.findByValue(value);
        GetContactMediumResponse response =
                ContactMediumMapper.INSTANCE.getContactMediumResponseFromContactMedium(contactMedium);
        return response;
    }

    @Override
    public List<GetListContactMediumResponse> getListByType(String type) {
        List<ContactMedium> contactMediums = contactMediumRepository.findByType(type);
        List<GetListContactMediumResponse> responses =
                ContactMediumMapper.INSTANCE.getListContactMediumResponsesFromContactMedium(contactMediums);
        return responses;
    }

    @Override
    public List<GetListContactMediumResponse> getListByCustomerId(int customerId) {
        List<ContactMedium> contactMediums = contactMediumRepository.findByCustomerId(customerId);
        List<GetListContactMediumResponse> responses = ContactMediumMapper.INSTANCE.getListContactMediumResponsesFromContactMedium(contactMediums);
        return responses;
    }
}
