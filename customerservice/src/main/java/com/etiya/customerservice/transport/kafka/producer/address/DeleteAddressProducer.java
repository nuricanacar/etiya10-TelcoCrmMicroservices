package com.etiya.customerservice.transport.kafka.producer.address;

import com.etiya.common.events.address.CreateAddressEvent;
import com.etiya.common.events.address.DeleteAddressEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class DeleteAddressProducer {

    private final StreamBridge streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteAddressProducer.class);

    public DeleteAddressProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void produceAddressDeleted(DeleteAddressEvent event){
        streamBridge.send("addressDeleted-out-0", event);
        LOGGER.info(String.format("Address deleted event => %s",event.addressId()));
    }
}
