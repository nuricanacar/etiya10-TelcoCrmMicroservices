package com.etiya.searchservice.transport.kafka.contactMedium.consumer;

import com.etiya.common.events.address.UpdateAddressEvent;
import com.etiya.common.events.contactMedium.UpdateContactMediumEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.ContactMedium;
import com.etiya.searchservice.service.CustomerSearchService;
import com.etiya.searchservice.transport.kafka.address.consumer.UpdatedAddressConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class UpdatedContactMediumConsumer {
    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(UpdatedContactMediumConsumer.class);

    public UpdatedContactMediumConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<UpdateContactMediumEvent> contactmediumUpdated(){
        return event -> {
            ContactMedium contactMedium = new ContactMedium(
                    event.id(),
                    event.type(),
                    event.value(),
                    event.isPrimary()
            );
            customerSearchService.updateContactMedium(contactMedium, event.customerId().toString());
            LOGGER.info(String.format("Updated Contact Medium => %s", event.id()));
        };
    }
}
