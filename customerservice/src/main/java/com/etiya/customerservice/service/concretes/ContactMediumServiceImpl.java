package com.etiya.customerservice.service.concretes;

import com.etiya.common.events.contactMedium.CreateContactMediumEvent;
import com.etiya.common.events.contactMedium.DeleteContactMediumEvent;
import com.etiya.common.events.contactMedium.UpdateContactMediumEvent;
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
import com.etiya.customerservice.transport.kafka.producer.contactMedium.CreateContactMediumProducer;
import com.etiya.customerservice.transport.kafka.producer.contactMedium.DeleteContactMediumProducer;
import com.etiya.customerservice.transport.kafka.producer.contactMedium.UpdateContactMediumProducer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ContactMediumServiceImpl implements ContactMediumService {

    private final ContactMediumRepository contactMediumRepository;
    private final ContactMediumBusinessRules contactMediumBusinessRules;
    private final CustomerService customerService;
    private final CreateContactMediumProducer createContactMediumProducer;
    private final UpdateContactMediumProducer updateContactMediumProducer;
    private final DeleteContactMediumProducer deleteContactMediumProducer;

    public ContactMediumServiceImpl(ContactMediumRepository contactMediumRepository, ContactMediumBusinessRules contactMediumBusinessRules, CustomerService customerService, CreateContactMediumProducer createContactMediumProducer, UpdateContactMediumProducer updateContactMediumProducer, DeleteContactMediumProducer deleteContactMediumProducer) {
        this.contactMediumRepository = contactMediumRepository;
        this.contactMediumBusinessRules = contactMediumBusinessRules;
        this.customerService = customerService;
        this.createContactMediumProducer = createContactMediumProducer;
        this.updateContactMediumProducer = updateContactMediumProducer;
        this.deleteContactMediumProducer = deleteContactMediumProducer;
    }


    @Override
    public CreatedContactMediumResponse add(CreateContactMediumRequest request) {
        ContactMedium contactMedium = ContactMediumMapper.INSTANCE.getContactMediumFromCreateContactMediumRequest(request);
        contactMediumBusinessRules.checkIsPrimaryOnlyOne(contactMedium);
        customerService.existsById(request.getCustomerId());
        ContactMedium created =  contactMediumRepository.save(contactMedium);
        CreateContactMediumEvent event = new CreateContactMediumEvent(
                created.getId(),
                created.getType().name(),
                created.getValue(),
                created.isPrimary(),
                created.getCustomer().getId()
        );
        createContactMediumProducer.produceContactMediumCreated(event);
        CreatedContactMediumResponse response = ContactMediumMapper.INSTANCE.getCreatedContactMediumResponseFromContactMedium(created);
        return response;
    }

    @Override
    public UpdatedContactMediumResponse update(UpdateContactMediumRequest request) {
        ContactMedium contactMediumOld = contactMediumRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Contact Medium not found"));

        ContactMedium contactMedium = ContactMediumMapper.INSTANCE.contactMediumFromUpdateContactMediumRequest(request, contactMediumOld);
        ContactMedium updated = contactMediumRepository.save(contactMedium);
        UpdateContactMediumEvent event = new UpdateContactMediumEvent(
                updated.getId(),
                updated.getType().name(),
                updated.getValue(),
                updated.isPrimary(),
                updated.getCustomer().getId()
        );
        updateContactMediumProducer.produceContactMediumUpdated(event);
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
        DeleteContactMediumEvent event = new DeleteContactMediumEvent(id,contactMedium.getCustomer().getId());
        contactMediumRepository.delete(contactMedium);
        deleteContactMediumProducer.produceContactMediumDeleted(event);
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
    public List<GetListContactMediumResponse> getListByCustomerId(UUID customerId) {
        List<ContactMedium> contactMediums = contactMediumRepository.findByCustomerId(customerId);
        List<GetListContactMediumResponse> responses = ContactMediumMapper.INSTANCE.getListContactMediumResponsesFromContactMedium(contactMediums);
        return responses;
    }
}
