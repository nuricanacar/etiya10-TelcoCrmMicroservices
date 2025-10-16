package com.etiya.common.events.address;

public record UpdateAddressEvent(int addressId, String street, String houseNumber,
                                 String description, boolean isDefault,
                                 int districtId, String customerId) {
}