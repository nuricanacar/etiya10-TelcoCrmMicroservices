package com.etiya.customerservice.transport.kafka.producer.contactMedium;

import com.etiya.common.events.address.CreateAddressEvent;
import com.etiya.common.events.contactMedium.CreateContactMediumEvent;
import com.etiya.customerservice.transport.kafka.producer.address.CreateAddressProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class CreateContactMediumProducer {

    //private final KafkaTemplate<String, CreateAddressEvent> kafkaTemplate;

    private final StreamBridge streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAddressProducer.class);

    public CreateContactMediumProducer(/*KafkaTemplate<String, CreateAddressEvent> kafkaTemplate,*/ StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
        //this.kafkaTemplate = kafkaTemplate;
    }

    public void produceContactMediumCreated(CreateContactMediumEvent event){
        streamBridge.send("contactmediumCreated-out-0", event);
        LOGGER.info(String.format("Customer created event => %s",event.customerId()));

        /*
        Message<CreateContactMediumEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"create-contact-medium").build();
        kafkaTemplate.send(message);
        */
    }
}
