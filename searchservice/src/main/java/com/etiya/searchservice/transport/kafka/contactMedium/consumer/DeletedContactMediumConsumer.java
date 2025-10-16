package com.etiya.searchservice.transport.kafka.contactMedium.consumer;

import com.etiya.common.events.address.DeleteAddressEvent;
import com.etiya.common.events.contactMedium.DeleteContactMediumEvent;
import com.etiya.searchservice.service.CustomerSearchService;
import com.etiya.searchservice.transport.kafka.address.consumer.DeletedAddressConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class DeletedContactMediumConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(DeletedContactMediumConsumer.class);

    public DeletedContactMediumConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<DeleteContactMediumEvent> contactmediumDeleted(){
        return event -> {
            customerSearchService.deleteContactMedium(event.id(), event.customerId().toString());
            LOGGER.info(String.format("Deleted Customer => %s", event.id()));
        };
    }
}
