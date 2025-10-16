package com.etiya.customerservice.transport.kafka.producer.address;

import com.etiya.common.events.address.UpdateAddressEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class UpdateAddressProducer {
    //private final KafkaTemplate<String, UpdateAddressEvent> kafkaTemplate;

    private final StreamBridge streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateAddressProducer.class);

    public UpdateAddressProducer(/*KafkaTemplate<String, UpdateAddressEvent> kafkaTemplate,*/ StreamBridge streamBridge) {
        //this.kafkaTemplate = kafkaTemplate;
        this.streamBridge = streamBridge;
    }

    public void produceAddressUpdated(UpdateAddressEvent event){
        streamBridge.send("addressUpdated-out-0", event);
        LOGGER.info(String.format("Address update event => %s",event.addressId()));

        /*
        Message<UpdateAddressEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"update-address1").build();
        kafkaTemplate.send(message);

         */
    }
}