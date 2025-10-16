package com.etiya.searchservice.transport.kafka.address.consumer;

import com.etiya.common.events.address.UpdateAddressEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Configuration
public class UpdatedAddressConsumer {
    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(UpdatedAddressConsumer.class);

    public UpdatedAddressConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<UpdateAddressEvent> addressUpdated(){
        return event -> {
            Address address = new Address(
                    event.addressId(),
                    event.houseNumber(),
                    event.description(),
                    event.street(),
                    event.isDefault(),
                    event.districtId()
            );
            customerSearchService.updateAddress(address, event.customerId());
            LOGGER.info(String.format("Updated Address => %s", event.addressId()));
        };
    }

    /*
    @KafkaListener(topics = "update-address1", groupId = "update-address-group1")
    public void consume(UpdateAddressEvent event) {
        LOGGER.info(String.format("Updated Address => %s", event.addressId()));
        Address address = new Address(
                event.addressId(),
                event.houseNumber(),
                event.description(),
                event.street(),
                event.isDefault(),
                event.districtId()
        );
        customerSearchService.updateAddress(address, event.customerId());
    }

     */
}
