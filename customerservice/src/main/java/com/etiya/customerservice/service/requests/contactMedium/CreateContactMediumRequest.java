package com.etiya.customerservice.service.requests.contactMedium;

import com.etiya.common.validators.annotations.ContactFormat;
import com.etiya.common.validators.annotations.EnumValidator;
import com.etiya.customerservice.domain.enums.ContactMediumType;
import com.etiya.customerservice.service.messages.Messages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@ContactFormat( // 🎯 İŞTE YENİ ANOTASYONU BURADA KULLANIYORUZ
        typeField = "type",
        valueField = "value",
        message = Messages.ContactTypeValueMatchCheck
)

public class CreateContactMediumRequest {
    @NotBlank(message = Messages.ContactMediumTypeRequired)
    @EnumValidator(
            enumClass = ContactMediumType.class,
            message = Messages.ContactTypeShouldValid
    )
    private String type;

    @NotBlank
    @Size(max = 150, message = Messages.ContactMediumValueSize)
    private String value;


    private boolean isPrimary;
    @NotNull(message = Messages.CustomerIdRequired)
    private UUID customerId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public CreateContactMediumRequest(String type, String value, boolean isPrimary, UUID customerId) {
        this.type = type;
        this.value = value;
        this.isPrimary = isPrimary;
        this.customerId = customerId;
    }

    public CreateContactMediumRequest() {
    }
}
