package com.etiya.searchservice.transport.kafka.address.consumer;

import com.etiya.common.events.address.CreateAddressEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.service.CustomerSearchService;
import com.etiya.searchservice.transport.kafka.customer.consumer.CreatedCustomerConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Configuration
public class CreatedAddressConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(CreatedAddressConsumer.class);

    public CreatedAddressConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<CreateAddressEvent> addressCreated(){
        return event -> {
            Address address = new Address(
                    event.addressId(),
                    event.houseNumber(),
                    event.description(),
                    event.street(),
                    event.isDefault(),
                    event.districtId());
            customerSearchService.addAddress(address, event.customerId());
            LOGGER.info(String.format("Consumed Customer => %s", event.addressId()));
        };
    }

    /* @KafkaListener(topics = "create-address", groupId = "create-address-group")
    public void consume(CreateAddressEvent event) {
        LOGGER.info(String.format("Consumed Customer => %s", event.addressId()));
        Address address = new Address(
                event.addressId(),
                event.houseNumber(),
                event.description(),
                event.street(),
                event.isDefault(),
                event.districtId()
        );
        customerSearchService.addAddress(address, event.customerId());
    }

     */
}
