package com.etiya.searchservice.transport.kafka.contactMedium.consumer;

import com.etiya.common.events.address.CreateAddressEvent;
import com.etiya.common.events.contactMedium.CreateContactMediumEvent;
import com.etiya.searchservice.domain.ContactMedium;
import com.etiya.searchservice.service.CustomerSearchService;
import com.etiya.searchservice.transport.kafka.customer.consumer.CreatedCustomerConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.function.Consumer;

@Configuration
public class CreatedContactMediumConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(CreatedContactMediumConsumer.class);

    public CreatedContactMediumConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<CreateContactMediumEvent> contactmediumCreated(){
        return event -> {
            ContactMedium contactMedium = new ContactMedium(
                    event.id(),
                    event.type(),
                    event.value(),
                    event.isPrimary(),
                    event.customerId().toString()
            );
            customerSearchService.addContactMedium(contactMedium);
            LOGGER.info(String.format("Created contact medium => %s", event.id()));
        };
    }


    /*
    @KafkaListener(topics = "create-contact-medium", groupId = "create-contact-medium-group")
    public void consume(CreateContactMediumEvent event){
        LOGGER.info(String.format("Created contact medium => %s", event.id()));
        ContactMedium contactMedium = new ContactMedium(
                event.id(),
                event.type(),
                event.value(),
                event.isPrimary()
        );
        customerSearchService.addContactMedium(contactMedium, event.customerId().toString());
    }

     */
}
