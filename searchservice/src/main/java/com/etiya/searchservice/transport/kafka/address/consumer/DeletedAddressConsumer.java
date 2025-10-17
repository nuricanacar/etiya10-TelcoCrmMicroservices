package com.etiya.searchservice.transport.kafka.address.consumer;

import com.etiya.common.events.address.CreateAddressEvent;
import com.etiya.common.events.address.DeleteAddressEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class DeletedAddressConsumer {
    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(DeletedAddressConsumer.class);

    public DeletedAddressConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<DeleteAddressEvent> addressDeleted(){
        return event -> {
            Address address = new Address(
                    event.addressId(),
                    event.customerId()
            );
            customerSearchService.deleteAddress(address);
            LOGGER.info(String.format("Deleted Customer => %s", event.addressId()));
        };
    }
}
