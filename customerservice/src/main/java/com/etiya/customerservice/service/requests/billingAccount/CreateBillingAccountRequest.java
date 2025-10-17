package com.etiya.customerservice.service.requests.billingAccount;

import com.etiya.customerservice.domain.enums.BillingAccountType;
import com.etiya.customerservice.service.messages.Messages;
import jakarta.validation.constraints.*;

import java.util.UUID;

public class CreateBillingAccountRequest {
    @NotBlank(message = Messages.AccountNameRequired)
    @Size(min = 3,max = 100,message = Messages.AccountNameSize)
    @Pattern(regexp = "^[a-zA-Z0-9şıüğöçŞİÜĞÖÇ -]+$", message = Messages.AccountNamePattern)
    private String accountName;

    @NotNull(message = Messages.BillingAccountTypeRequired)
    private BillingAccountType type;

    @NotNull(message = Messages.CustomerIdRequired)
    private UUID customerId;

    @NotNull(message = Messages.AddressIdRequired)
    private int addressId;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public BillingAccountType getType() {
        return type;
    }

    public void setType(BillingAccountType type) {
        this.type = type;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public CreateBillingAccountRequest(String accountName, BillingAccountType type, UUID customerId, int addressId) {
        this.accountName = accountName;
        this.type = type;
        this.customerId = customerId;
        this.addressId = addressId;
    }

    public CreateBillingAccountRequest() {
    }
}
