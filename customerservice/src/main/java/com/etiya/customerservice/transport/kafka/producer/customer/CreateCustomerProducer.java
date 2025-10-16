package com.etiya.customerservice.transport.kafka.producer.customer;

import com.etiya.common.events.customer.CreateCustomerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerProducer {
    //mesajları kafkaya gönderen spring bean tanımalmalıyım
    //message brokera eventi göstermek için yazılan yapıdır.
    //private final KafkaTemplate<String, CreateCustomerEvent> kafkaTemplate;
    private final StreamBridge streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateCustomerProducer.class);

    public CreateCustomerProducer(/*KafkaTemplate<String, CreateCustomerEvent> kafkaTemplate, */StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
        //this.kafkaTemplate = kafkaTemplate;
    }

    public void produceCustomerCreated(CreateCustomerEvent event) {
//bu eventi dışarıya açıyorum.
        streamBridge.send("customerCreated-out-0", event);

        LOGGER.info(String.format("Customer created event => %s", event.customerId()));

//        Message<CreateCustomerEvent> message = MessageBuilder.withPayload(event)
//                .setHeader(KafkaHeaders.TOPIC, "create-customer").build();
//        kafkaTemplate.send(message);
    }
}
//Burda doğrudan kafkaya olan bağımlılığı bitirdim. Stream bridge bizim için ne kullanırsan kullan ben bunu kendi sınıfım üstünden yaparım.