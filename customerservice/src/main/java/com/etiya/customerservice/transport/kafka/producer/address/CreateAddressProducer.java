package com.etiya.customerservice.transport.kafka.producer.address;


import com.etiya.common.events.address.CreateAddressEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class CreateAddressProducer {
    //private final KafkaTemplate<String, CreateAddressEvent> kafkaTemplate;
    private final StreamBridge streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAddressProducer.class);

    public CreateAddressProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }


    public void produceAddressCreated(CreateAddressEvent event){

        streamBridge.send("addressCreated-out-0", event);
        LOGGER.info(String.format("Address created event => %s",event.addressId()));

//        Message<CreateAddressEvent> message = MessageBuilder.withPayload(event)
//                .setHeader(KafkaHeaders.TOPIC,"create-address1").build();
//        kafkaTemplate.send(message);
    }
}