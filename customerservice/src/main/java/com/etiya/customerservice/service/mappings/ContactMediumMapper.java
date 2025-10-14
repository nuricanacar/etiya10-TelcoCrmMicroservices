package com.etiya.customerservice.service.mappings;

import com.etiya.customerservice.domain.entities.ContactMedium;
import com.etiya.customerservice.service.requests.contactMedium.CreateContactMediumRequest;
import com.etiya.customerservice.service.requests.contactMedium.UpdateContactMediumRequest;
import com.etiya.customerservice.service.responses.contactMedium.CreatedContactMediumResponse;
import com.etiya.customerservice.service.responses.contactMedium.GetContactMediumResponse;
import com.etiya.customerservice.service.responses.contactMedium.GetListContactMediumResponse;
import com.etiya.customerservice.service.responses.contactMedium.UpdatedContactMediumResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContactMediumMapper {

    ContactMediumMapper INSTANCE = Mappers.getMapper(ContactMediumMapper.class);

    @Mapping(source = "customerId",target = "customer.id")
    ContactMedium getContactMediumFromCreateContactMediumRequest(CreateContactMediumRequest createContactMediumRequest);

    CreatedContactMediumResponse getCreatedContactMediumResponseFromContactMedium(ContactMedium contactMedium);

    @Mapping(source = "customer.id",target = "customerId")
    @Mapping(source = "type", target = "type")
    GetListContactMediumResponse toGetListResponse(ContactMedium contactMedium);

    @Mapping(source = "customer.id",target = "customerId")
    List<GetListContactMediumResponse> getListContactMediumResponsesFromContactMedium(List<ContactMedium> contactMediums);



    @Mapping(target = "customer.id",source = "customerId")
    ContactMedium contactMediumFromUpdateContactMediumRequest(UpdateContactMediumRequest updateContactMediumRequest);

    ContactMedium contactMediumFromUpdateContactMediumRequest(UpdateContactMediumRequest updateContactMediumRequest, @MappingTarget ContactMedium contactMedium);

    @Mapping(target = "customerId",source = "customer.id")
    UpdatedContactMediumResponse getUpdatedContactMediumResponseFromContactMedium(ContactMedium contactMedium);

    GetContactMediumResponse getContactMediumResponseFromContactMedium(ContactMedium contactMedium);
}