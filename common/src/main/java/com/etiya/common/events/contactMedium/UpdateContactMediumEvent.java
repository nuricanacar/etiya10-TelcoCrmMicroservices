package com.etiya.common.events.contactMedium;

import java.util.UUID;

public record UpdateContactMediumEvent(int id,
                                       String type,
                                       String value,
                                       boolean isPrimary,
                                       UUID customerId) {
}
