package com.etiya.searchservice.transport.kafka.customer.consumer;

import com.etiya.common.events.customer.CreateCustomerEvent;
import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

//@Service
//kafka listenerla çalışmadığı için artık service değil konfigurasyon
@Configuration
public class CreatedCustomerConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(CreatedCustomerConsumer.class);

    public CreatedCustomerConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<CreateCustomerEvent> customerCreated(){
        return event -> {
            CustomerSearch customerSearch = new CustomerSearch(event.customerId(),
                event.customerNumber(),
                event.firstName(),
                event.lastName(), event.nationalId(), event.dateOfBirth(), event.fatherName(), event.fatherName(), event.gender());
            customerSearchService.add(customerSearch);
            LOGGER.info(String.format("Consumed Customer Event: %s", event.customerId()));
        };
    }





//
//    @KafkaListener(topics = "create-customer",groupId = "create-customer-group")
//    public void consume(CreateCustomerEvent event){
//        LOGGER.info(String.format("Consumed Customer Event: %s", event.customerId()));
//
//        CustomerSearch customerSearch = new CustomerSearch(event.customerId(),
//                event.customerNumber(),
//                event.firstName(),
//                event.lastName(), event.nationalId(), event.dateOfBirth(), event.fatherName(), event.fatherName(), event.gender());
//    customerSearchService.add(customerSearch);
//    }


}
