package com.etiya.customerservice.service.requests.address;

import com.etiya.common.localization.LocalizationService;
import com.etiya.customerservice.service.messages.Messages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.context.MessageSource;

import java.util.UUID;

import static com.etiya.customerservice.service.messages.Messages.StreetRequired;

public class CreateAddressRequest {




    //@LocalizedNotBlank(message = "address.street.required")
    @NotBlank(message = Messages.StreetRequired)
    //@NotBlank(message = "{streetRequired}")
    private String street;
    @NotBlank(message = Messages.HouseNumberRequired)
    private String houseNumber;
    @Size(max = 255, message = Messages.DescriptionSize)
    private String description;
    private boolean isDefault;
    private int districtId;
    private String customerId;

    public CreateAddressRequest(String street, String houseNumber, String description, boolean isDefault, int districtId, String customerId) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.description = description;
        this.isDefault = isDefault;
        this.districtId = districtId;
        this.customerId = customerId;

    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


}
