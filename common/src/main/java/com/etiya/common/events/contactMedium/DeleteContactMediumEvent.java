package com.etiya.common.events.contactMedium;

import java.util.UUID;

public record DeleteContactMediumEvent(int id,
                                       UUID customerId) {
}
