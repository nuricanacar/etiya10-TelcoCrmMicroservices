package com.etiya.common.events;

import java.time.LocalDateTime;
import java.util.List;

public record CreateCustomerEvent(String customerId, String customerNumber, String firstName, String lastName, String nationalId,
                                  LocalDateTime dateOfBirth, String motherName, String fatherName, String gender ) {
}
