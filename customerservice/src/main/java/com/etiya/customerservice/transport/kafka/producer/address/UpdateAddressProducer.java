package com.etiya.customerservice.transport.kafka.producer.address;

import com.etiya.common.events.UpdateAddressEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class UpdateAddressProducer {
    private final KafkaTemplate<String, UpdateAddressEvent> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateAddressProducer.class);

    public UpdateAddressProducer(KafkaTemplate<String, UpdateAddressEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceAddressUpdated(UpdateAddressEvent event){
        LOGGER.info(String.format("Address updated event => %s",event.addressId()));

        Message<UpdateAddressEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"update-address1").build();
        kafkaTemplate.send(message);
    }
}
