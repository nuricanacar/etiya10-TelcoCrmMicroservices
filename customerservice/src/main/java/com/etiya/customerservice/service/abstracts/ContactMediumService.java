package com.etiya.customerservice.service.abstracts;

import com.etiya.customerservice.service.requests.contactMedium.CreateContactMediumRequest;
import com.etiya.customerservice.service.requests.contactMedium.UpdateContactMediumRequest;
import com.etiya.customerservice.service.responses.contactMedium.CreatedContactMediumResponse;
import com.etiya.customerservice.service.responses.contactMedium.GetContactMediumResponse;
import com.etiya.customerservice.service.responses.contactMedium.GetListContactMediumResponse;
import com.etiya.customerservice.service.responses.contactMedium.UpdatedContactMediumResponse;

import java.util.List;
import java.util.UUID;

public interface ContactMediumService {
    CreatedContactMediumResponse add(CreateContactMediumRequest request);
    UpdatedContactMediumResponse update(UpdateContactMediumRequest request);
    List<GetListContactMediumResponse> getList();

    void delete(int id);

    void softDelete(int id);

    GetContactMediumResponse getById(int id);
    GetContactMediumResponse getByValue(String value);
    List<GetListContactMediumResponse> getListByType(String type);
    List<GetListContactMediumResponse> getListByCustomerId(UUID customerId);
}